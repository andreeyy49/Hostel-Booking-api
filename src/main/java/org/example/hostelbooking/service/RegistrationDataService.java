package org.example.hostelbooking.service;

import lombok.RequiredArgsConstructor;
import org.example.hostelbooking.entity.RegistrationData;
import org.example.hostelbooking.repository.RegistrationDataRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class RegistrationDataService {

    private final RegistrationDataRepository registrationDataRepository;

    public void add(RegistrationData registrationData) {
        registrationDataRepository.save(registrationData);
    }

    public Optional<RegistrationData> getById(String registrationId) {
        return registrationDataRepository.findById(registrationId);
    }

    public List<RegistrationData> findAll() {
        return registrationDataRepository.findAll();
    }
}
