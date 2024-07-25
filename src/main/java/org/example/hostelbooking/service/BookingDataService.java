package org.example.hostelbooking.service;

import lombok.RequiredArgsConstructor;
import org.example.hostelbooking.entity.BookingData;
import org.example.hostelbooking.repository.BookingDataRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class BookingDataService {

    private final BookingDataRepository bookingDataRepository;

    public Optional<BookingData> getById(String bookingId) {
        return bookingDataRepository.findById(bookingId);
    }

    public void add(BookingData bookingData) {
        bookingDataRepository.save(bookingData);
    }

    public List<BookingData> findAll() {
        return bookingDataRepository.findAll();
    }
}
