package com.mouvie.library.repository;

import com.mouvie.library.model.Room;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RoomLibRepository extends JpaRepository<Room, String> {
}
