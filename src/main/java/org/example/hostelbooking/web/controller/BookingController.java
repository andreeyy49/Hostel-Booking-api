package org.example.hostelbooking.web.controller;

import lombok.RequiredArgsConstructor;
import org.example.hostelbooking.entity.Booking;
import org.example.hostelbooking.mapper.BookingMapper;
import org.example.hostelbooking.service.BookingService;
import org.example.hostelbooking.web.entity.booking.BookingListResponse;
import org.example.hostelbooking.web.entity.booking.BookingResponse;
import org.example.hostelbooking.web.entity.booking.UpsertBookingRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
    public ResponseEntity<BookingListResponse> findAll(){
        return ResponseEntity.ok().body(bookingMapper.bookingListToBookingListResponse(bookingService.findAll()));
    }

    @GetMapping("/{id}")
    public ResponseEntity<BookingResponse> findById(@PathVariable Long id){
        return ResponseEntity.ok().body(bookingMapper.bookingToResponse(bookingService.findById(id)));
    }

    @PostMapping
    public ResponseEntity<BookingResponse> create(@RequestBody UpsertBookingRequest upsertBookingRequest){
        Booking booking = bookingMapper.requestToBooking(upsertBookingRequest);
        booking = bookingService.save(booking);

        return ResponseEntity.status(HttpStatus.CREATED).body(bookingMapper.bookingToResponse(booking));
    }

    @PutMapping("/{id}")
    public ResponseEntity<BookingResponse> update(@PathVariable Long id, @RequestBody UpsertBookingRequest upsertBookingRequest){
        Booking booking = bookingMapper.requestToBooking(upsertBookingRequest);
        booking.setId(id);
        booking = bookingService.update(booking);

        return ResponseEntity.ok().body(bookingMapper.bookingToResponse(booking));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id){
        bookingService.delete(id);

        return ResponseEntity.noContent().build();
    }

}
