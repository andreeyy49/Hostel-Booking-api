package org.example.hostelbooking.service;

import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.example.hostelbooking.entity.Booking;
import org.example.hostelbooking.repository.BookingRepository;
import org.example.hostelbooking.utils.BeanUtils;
import org.springframework.stereotype.Service;

import java.text.MessageFormat;
import java.util.List;

@RequiredArgsConstructor
@Service
public class BookingService {

    private final BookingRepository bookingRepository;

    public List<Booking> findAll() {
        return bookingRepository.findAll();
    }

    public Booking findById(Long id) {
        return bookingRepository.findById(id).orElseThrow(() -> new EntityNotFoundException(MessageFormat
                .format("Booking with id {0} not found", id)));
    }

    public Booking save(Booking booking) {
        return bookingRepository.save(booking);
    }

    public Booking update(Booking booking) {
        Booking existBooking = findById(booking.getId());

        BeanUtils.copyNotNullProperties(booking, existBooking);

        return bookingRepository.save(booking);
    }

    public void delete(Long id) {
        bookingRepository.deleteById(id);
    }
}
