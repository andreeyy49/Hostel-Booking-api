package org.example.hostelbooking.web.controller;

import lombok.RequiredArgsConstructor;
import org.example.hostelbooking.entity.Booking;
import org.example.hostelbooking.mapper.BookingMapper;
import org.example.hostelbooking.service.BookingService;
import org.example.hostelbooking.web.dto.booking.BookingListResponse;
import org.example.hostelbooking.web.dto.booking.BookingResponse;
import org.example.hostelbooking.web.dto.booking.UpsertBookingRequest;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/booking")
@RequiredArgsConstructor
public class BookingController {

    private final BookingService bookingService;

    private final BookingMapper bookingMapper;

    @GetMapping
    @PreAuthorize("hasAnyRole('ROLE_ADMIN')")
    public BookingListResponse findAll(){
        return bookingMapper.bookingListToBookingListResponse(bookingService.findAll());
    }

    @GetMapping("/{id}")
    public BookingResponse findById(@PathVariable Long id){
        return bookingMapper.bookingToResponse(bookingService.findById(id));
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public BookingResponse create(@RequestBody UpsertBookingRequest upsertBookingRequest){
        Booking booking = bookingMapper.requestToBooking(upsertBookingRequest);
        booking = bookingService.save(booking);

        return bookingMapper.bookingToResponse(booking);
    }

    @PutMapping("/{id}")
    public BookingResponse update(@PathVariable Long id, @RequestBody UpsertBookingRequest upsertBookingRequest){
        Booking booking = bookingMapper.requestToBooking(upsertBookingRequest);
        booking.setId(id);
        booking = bookingService.update(booking);

        return bookingMapper.bookingToResponse(booking);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Long id){
        bookingService.delete(id);
    }
}
