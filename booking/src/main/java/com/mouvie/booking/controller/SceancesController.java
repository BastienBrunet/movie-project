package com.mouvie.booking.controller;

import com.mouvie.booking.dto.model.sceances.SceanceInputDto;
import com.mouvie.booking.dto.model.sceances.SceancesDto;
import com.mouvie.booking.service.sceances.SceancesService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/cinema/{cinemaUid}/rooms/{roomUid}/sceances")
@AllArgsConstructor
public class SceancesController {

    private final SceancesService sceancesService;

    @PreAuthorize("hasRole('ROLE_USER')")
    @GetMapping
    public ResponseEntity<List<SceancesDto>> getAllSceances(@PathVariable String cinemaUid, @PathVariable String roomUid){
        return ResponseEntity.ok(sceancesService.getAllSceances(cinemaUid, roomUid));
    }

    @PreAuthorize("hasRole('ROLE_USER')")
    @GetMapping("/{uid}")
    public ResponseEntity<SceancesDto> getSceance(@PathVariable String cinemaUid, @PathVariable String roomUid, @PathVariable String uid){
        return ResponseEntity.ok(sceancesService.getSceanceById(cinemaUid, roomUid, uid));
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @PostMapping
    public ResponseEntity<SceancesDto> createSceance(@PathVariable String cinemaUid, @PathVariable String roomUid, @RequestBody @Valid SceanceInputDto sceanceInputDto){
        return ResponseEntity.ok(sceancesService.createSceance(cinemaUid, roomUid, sceanceInputDto));
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @PutMapping("/{uid}")
    public ResponseEntity<SceancesDto> updateSceance(@PathVariable String cinemaUid, @PathVariable String roomUid,@PathVariable String uid, @RequestBody @Valid SceanceInputDto sceanceInputDto){
        return ResponseEntity.ok(sceancesService.updateSceance(cinemaUid, roomUid, uid, sceanceInputDto));
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @DeleteMapping("/{uid}")
    public ResponseEntity<String> deleteSceance(@PathVariable String cinemaUid, @PathVariable String roomUid,@PathVariable String uid){
        sceancesService.deleteSceance(cinemaUid, roomUid, uid);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
