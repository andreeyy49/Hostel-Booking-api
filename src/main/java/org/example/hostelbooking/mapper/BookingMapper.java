package org.example.hostelbooking.mapper;

import org.example.hostelbooking.entity.Booking;
import org.example.hostelbooking.entity.Room;
import org.example.hostelbooking.entity.User;
import org.example.hostelbooking.service.RoomService;
import org.example.hostelbooking.service.UserService;
import org.example.hostelbooking.web.dto.booking.*;
import org.mapstruct.*;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.stream.Collectors;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE, uses = {RoomMapper.class, UserMapper.class})
public abstract class BookingMapper {

    @Autowired
    protected RoomService roomService;

    @Autowired
    protected UserService userService;

    @Mapping(source = "userId", target = "user", qualifiedByName = "mapUser")
    @Mapping(source = "roomId", target = "room", qualifiedByName = "mapRoom")
    public abstract Booking requestToBooking(UpsertBookingRequest request);

    @Mapping(source = "bookingId", target = "id")
    public abstract Booking requestToBooking(UpsertBookingRequest request, Long bookingId);

    public abstract BookingResponse bookingToResponse(Booking booking);

    public abstract BookingResponseWithoutRoom bookingToResponseWithoutRoom(Booking booking);

    public abstract BookingResponseWithoutUser bookingToResponseWithoutUser(Booking booking);

    public BookingListResponse bookingListToBookingListResponse(List<Booking> bookings) {
        BookingListResponse bookingListResponse = new BookingListResponse();

        bookingListResponse.setBookings(bookings.stream()
                .map(this::bookingToResponse)
                .collect(Collectors.toList()));

        return bookingListResponse;
    }

    @Named(value = "mapRoom")
    Room mapRoom(Long roomId) {
        return roomService.findById(roomId);
    }

    @Named(value = "mapUser")
    User mapUser(Long userId){
        return userService.findById(userId);
    }
}
