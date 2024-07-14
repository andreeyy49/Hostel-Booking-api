package org.example.hostelbooking.repository;

import org.example.hostelbooking.entity.Booking;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BookingRepository extends JpaRepository<Booking, Long> {
}
