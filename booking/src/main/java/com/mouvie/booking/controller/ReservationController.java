package com.mouvie.booking.controller;

import com.mouvie.booking.dto.model.reservation.ReservationDto;
import com.mouvie.booking.dto.model.reservation.ReservationInputDto;
import com.mouvie.booking.service.reservation.ReservationService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
public class ReservationController {

    private final ReservationService reservationService;

    @PreAuthorize("hasRole('ROLE_USER')")
    @PostMapping("/movie/{movieUid}/reservations")
    public ResponseEntity<ReservationDto> enterReservationTunnel(@PathVariable String movieUid, @RequestBody @Valid ReservationInputDto reservationInputDto){
        return ResponseEntity.ok(reservationService.enterReservationTunnel(movieUid, reservationInputDto));
    }

    @PreAuthorize("hasRole('ROLE_USER')")
    @PostMapping("/reservations/{uid}/confirm")
    public ResponseEntity<String> confirmReservation(@PathVariable String uid){
        reservationService.confirmReservation(uid);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @GetMapping("/movie/{movieUid}/reservations")
    public ResponseEntity<List<ReservationDto>> getReservationsOfMovie(@PathVariable String movieUid){
        return ResponseEntity.ok(reservationService.getAllReservations(movieUid));
    }

    @PreAuthorize("hasRole('ROLE_USER')")
    @GetMapping("/reservations/{uid}")
    public ResponseEntity<ReservationDto> getReservation(@PathVariable String uid){
        return ResponseEntity.ok(reservationService.getReservationById(uid));
    }
}
