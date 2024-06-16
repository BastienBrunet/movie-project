package com.mouvie.booking.service.reservation;

import com.mouvie.booking.config.RabbitMQConfig;
import com.mouvie.booking.config.customexception.ElementNotFoundException;
import com.mouvie.booking.dto.mapper.reservation.ReservationDtoMapper;
import com.mouvie.booking.dto.model.rabbitmq.QueueDetails;
import com.mouvie.booking.dto.model.reservation.ReservationDto;
import com.mouvie.booking.dto.model.reservation.ReservationInputDto;
import com.mouvie.booking.repository.reservation.ReservationRepository;
import com.mouvie.booking.service.rabbitmq.RabbitMqService;
import com.mouvie.booking.service.sceances.SceancesService;
import com.mouvie.booking.service.status.reservation.ReservationStatusService;
import com.mouvie.booking.tools.factory.reservation.ReservationFactory;
import com.mouvie.library.exception.IncorrectParamException;
import com.mouvie.library.exception.ReservationExpiredException;
import com.mouvie.library.model.Reservation;
import com.mouvie.library.model.ReservationStatus;
import com.mouvie.library.model.Sceance;
import com.mouvie.library.tools.enumeration.ReservationStatusEnum;
import com.mouvie.library.tools.factory.EmailFactory;
import com.mouvie.security.config.appcontext.AppContext;
import lombok.AllArgsConstructor;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.List;
import java.util.Objects;

@Service
@AllArgsConstructor
public class ReservationService implements IReservationService {

    private final SceancesService sceancesService;
    private final RabbitMqService rabbitMqService;
    private final ReservationStatusService reservationStatusService;
    private final RabbitTemplate rabbitTemplate;
    private final AppContext appContext;

    private final ReservationRepository reservationRepository;

    @Override
    public ReservationDto enterReservationTunnel(String movieId, ReservationInputDto reservationInputDto) {
        Sceance sceance = sceancesService.getSceance(reservationInputDto.getSceance());
        ReservationStatus openReservationStatus = reservationStatusService.getReservationStatus(ReservationStatusEnum.OPEN);

        if (!sceance.getMovie().getId().equals(movieId)) throw new IncorrectParamException(String.format("Movie [id=%s] is not part of the sceance [id=%s]", sceance.getMovie().getId(), sceance.getId()));
        if (!sceance.getRoom().getId().equals(reservationInputDto.getRoom())) throw new IncorrectParamException(String.format("Room [id=%s] is not part of the sceance [id=%s]", sceance.getRoom().getId(), sceance.getId()));

        // check if there is enough seats available
        if (sceancesService.getAvailableSeatsForSceance(sceance.getId()) < reservationInputDto.getNbSeats()) throw new IncorrectParamException("Not enough seats left");

        // Get message count in queue to set the rank
        QueueDetails queueDetails = rabbitMqService.getQueueInformations(RabbitMQConfig.RESERVATION_QUEUE_NAME);

        // Create reservation object
        Reservation newReservation = ReservationFactory.createReservation(queueDetails.getMessages(), reservationInputDto, sceance, openReservationStatus, appContext.getCurrentUser());
        newReservation = reservationRepository.save(newReservation);

        // Publish message on queue
        rabbitTemplate.convertAndSend(RabbitMQConfig.RESERVATION_QUEUE_NAME, newReservation);

        return ReservationDtoMapper.toReservationDto(newReservation);
    }

    @Override
    public List<ReservationDto> getAllReservations(String movieId) {
        return reservationRepository.getReservationsByMovieId(movieId);
    }

    @Override
    public ReservationDto getReservationById(String id) {
        Reservation reservation = getReservation(id);

        return ReservationDtoMapper.toReservationDto(reservation);
    }

    @Override
    public void confirmReservation(String id) {

        // Get reservation by id
        Reservation reservationToConfirm = getReservation(id);

        // Check if reservation is still in open status and is not expired
        if (isReservationStillValid(reservationToConfirm)) {

            // check if there is enough seats available
            if (sceancesService.getAvailableSeatsForSceance(reservationToConfirm.getSceance().getId()) < reservationToConfirm.getSeats()) throw new IncorrectParamException("Not enough seats left");

            reservationToConfirm.setStatus(reservationStatusService.getReservationStatus(ReservationStatusEnum.CONFIRMED));
            reservationRepository.save(reservationToConfirm);

            // Send email to user
            rabbitTemplate.convertAndSend(RabbitMQConfig.EMAIL_QUEUE_NAME, EmailFactory.buildReservationConfirmedEmail(appContext.getCurrentUser().getUsername(), reservationToConfirm));

        } else {
            // Set status to expired
            reservationToConfirm.setStatus(reservationStatusService.getReservationStatus(ReservationStatusEnum.EXPIRED));
            reservationRepository.save(reservationToConfirm);
            // Return 410 error
            throw new ReservationExpiredException(String.format("Reservation [id=%s] has expired", id));
        }
    }

    @Override
    public Reservation getReservation(String id){
        return reservationRepository.findById(id).orElseThrow(() -> new ElementNotFoundException(String.format("Unable to find Reservation [id = %s]", id)));
    }

    private boolean isReservationStillValid(Reservation reservation){
        return Objects.equals(reservation.getStatus().getId(), ReservationStatusEnum.OPEN.getId())
                && Instant.now().isBefore(reservation.getExpiresAt());
    }
}
