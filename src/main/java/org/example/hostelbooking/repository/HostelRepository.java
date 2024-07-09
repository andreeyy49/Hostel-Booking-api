package org.example.hostelbooking.repository;

import org.example.hostelbooking.entity.Hostel;
import org.springframework.data.jpa.repository.JpaRepository;

public interface HostelRepository extends JpaRepository<Hostel, Long> {
}
