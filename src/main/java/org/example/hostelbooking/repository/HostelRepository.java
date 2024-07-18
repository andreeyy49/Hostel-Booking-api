package org.example.hostelbooking.repository;

import org.example.hostelbooking.entity.Hostel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface HostelRepository extends JpaRepository<Hostel, Long>, JpaSpecificationExecutor<Hostel> {
}
