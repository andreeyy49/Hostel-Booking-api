package org.example.hostelbooking.repository;

import org.example.hostelbooking.entity.BookingData;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface BookingDataRepository extends MongoRepository<BookingData, String> {
}
