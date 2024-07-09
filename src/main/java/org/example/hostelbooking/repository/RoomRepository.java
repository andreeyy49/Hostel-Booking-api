package org.example.hostelbooking.repository;

import org.example.hostelbooking.entity.Room;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoomRepository extends JpaRepository<Room, Long> {
}
