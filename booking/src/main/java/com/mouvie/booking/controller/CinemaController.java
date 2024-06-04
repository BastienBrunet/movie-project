package com.mouvie.booking.controller;

import com.mouvie.booking.dto.model.cinema.CinemaDto;
import com.mouvie.booking.dto.model.cinema.CinemaInputDto;
import com.mouvie.booking.service.cinema.CinemaService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/cinema")
@AllArgsConstructor
public class CinemaController {

    private final CinemaService cinemaService;

    @PreAuthorize("hasRole('ROLE_USER')")
    @GetMapping
    public ResponseEntity<List<CinemaDto>> getAllCinemas(){
        return ResponseEntity.ok(cinemaService.getAllCinemas());
    }

    @PreAuthorize("hasRole('ROLE_USER')")
    @GetMapping("/{uid}")
    public ResponseEntity<CinemaDto> getCinema(@PathVariable String uid){
        return ResponseEntity.ok(cinemaService.getCinemaById(uid));
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @PostMapping
    public ResponseEntity<CinemaDto> createCinema(@RequestBody @Valid CinemaInputDto cinemaInputDto){
        return ResponseEntity.ok(cinemaService.createCinema(cinemaInputDto));
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @PutMapping("/{uid}")
    public ResponseEntity<CinemaDto> updateCinema(@PathVariable String uid, @RequestBody @Valid CinemaInputDto cinemaInputDto){
        return ResponseEntity.ok(cinemaService.updateCinema(uid, cinemaInputDto));
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @DeleteMapping("/{uid}")
    public ResponseEntity<String> deleteCinema(@PathVariable String uid){
        cinemaService.deleteCinema(uid);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
