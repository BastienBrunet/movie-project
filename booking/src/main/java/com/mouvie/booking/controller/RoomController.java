package com.mouvie.booking.controller;

import com.mouvie.booking.dto.model.room.RoomDto;
import com.mouvie.booking.dto.model.room.RoomInputDto;
import com.mouvie.booking.service.room.RoomService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/cinema/{cinemaUid}/rooms")
@AllArgsConstructor
public class RoomController {

    private final RoomService roomService;

    @PreAuthorize("hasRole('ROLE_USER')")
    @GetMapping
    public ResponseEntity<List<RoomDto>> getAllRooms(@PathVariable String cinemaUid){
        return ResponseEntity.ok(roomService.getRooms(cinemaUid));
    }

    @PreAuthorize("hasRole('ROLE_USER')")
    @GetMapping("/{uid}")
    public ResponseEntity<RoomDto> getRoomDetail(@PathVariable String cinemaUid, @PathVariable String uid){
        return ResponseEntity.ok(roomService.getRoom(cinemaUid, uid));
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @PostMapping
    public ResponseEntity<RoomDto> createRoom(@PathVariable String cinemaUid, @RequestBody @Valid RoomInputDto roomInputDto){
        return ResponseEntity.ok(roomService.createRoom(cinemaUid, roomInputDto));
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @PutMapping("/{uid}")
    public ResponseEntity<RoomDto> updateRoom(@PathVariable String cinemaUid, @PathVariable String uid, @RequestBody @Valid RoomInputDto roomInputDto){
        return ResponseEntity.ok(roomService.updateRoom(cinemaUid, uid, roomInputDto));
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @DeleteMapping("/{uid}")
    public ResponseEntity<String> deleteRoom(@PathVariable String cinemaUid, @PathVariable String uid){
        roomService.deleteRoom(cinemaUid, uid);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
