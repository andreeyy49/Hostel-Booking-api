package org.example.hostelbooking.repository;

import org.example.hostelbooking.entity.RegistrationData;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface RegistrationDataRepository extends MongoRepository<RegistrationData, String> {
}
