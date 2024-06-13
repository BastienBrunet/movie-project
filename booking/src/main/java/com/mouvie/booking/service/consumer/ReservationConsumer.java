package com.mouvie.booking.service.consumer;

import com.mouvie.booking.repository.reservation.ReservationRepository;
import com.mouvie.library.model.Reservation;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;

import java.time.Instant;

@Service
@Slf4j
@AllArgsConstructor
public class ReservationConsumer {

    private final ReservationRepository reservationRepository;

    @RabbitListener(queues = {"q.reservation"})
    public void consume(Reservation newReservation) {
        log.info("Received new reservation : " + newReservation.getId());

        newReservation.setExpiresAt(Instant.now().plusSeconds(60*10L)); // Expires in 10mn

        reservationRepository.save(newReservation);
    }

}
