package org.example.hostelbooking.service;

import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.example.hostelbooking.entity.Hostel;
import org.example.hostelbooking.repository.HostelRepository;
import org.example.hostelbooking.utils.BeanUtils;
import org.springframework.stereotype.Service;

import java.text.MessageFormat;
import java.util.List;

@Service
@RequiredArgsConstructor
public class HostelService {

    private final HostelRepository hostelRepository;

    public List<Hostel> findAll() {
        return hostelRepository.findAll();
    }

    public Hostel findById(String id) {
        return hostelRepository.findById(id).orElseThrow(() -> new EntityNotFoundException(MessageFormat.format(
                "Hostel with ID: {0} not found", id
        )));
    }

    public Hostel save(Hostel hostel) {
        return hostelRepository.save(hostel);
    }

    public Hostel update(Hostel hostel, String id) {
        Hostel existHostel = hostelRepository.findById(id).orElseThrow(() -> new EntityNotFoundException(
                "Hostel not found"
        ));

        BeanUtils.copyNotNullProperties(hostel, existHostel);

        return hostelRepository.save(hostel);
    }

    public void delete(String id) {
        hostelRepository.deleteById(id);
    }
}
