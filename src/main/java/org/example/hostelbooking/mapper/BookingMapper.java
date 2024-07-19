package org.example.hostelbooking.mapper;

import org.example.hostelbooking.entity.Booking;
import org.example.hostelbooking.web.dto.booking.*;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;

import java.util.List;
import java.util.stream.Collectors;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE, uses = {RoomMapper.class, UserMapper.class})
public interface BookingMapper {

    Booking requestToBooking(UpsertBookingRequest request);

    @Mapping(source = "bookingId", target = "id")
    Booking requestToBooking(UpsertBookingRequest request, Long bookingId);

    BookingResponse bookingToResponse(Booking booking);

    BookingResponseWithoutRoom bookingToResponseWithoutRoom(Booking booking);

    BookingResponseWithoutUser bookingToResponseWithoutUser(Booking booking);

    default BookingListResponse bookingListToBookingListResponse(List<Booking> bookings) {
        BookingListResponse bookingListResponse = new BookingListResponse();

        bookingListResponse.setBookings(bookings.stream()
                .map(this::bookingToResponse)
                .collect(Collectors.toList()));

        return bookingListResponse;
    }

}
