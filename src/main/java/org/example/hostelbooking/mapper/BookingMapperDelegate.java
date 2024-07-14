package org.example.hostelbooking.mapper;

import org.example.hostelbooking.entity.Booking;
import org.example.hostelbooking.service.RoomService;
import org.example.hostelbooking.service.UserService;
import org.example.hostelbooking.web.entity.booking.BookingResponse;
import org.example.hostelbooking.web.entity.booking.BookingResponseWithoutRoom;
import org.example.hostelbooking.web.entity.booking.BookingResponseWithoutUser;
import org.example.hostelbooking.web.entity.booking.UpsertBookingRequest;
import org.springframework.beans.factory.annotation.Autowired;

public abstract class BookingMapperDelegate implements BookingMapper {

    @Autowired
    private UserService userService;

    @Autowired
    private RoomService roomService;

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private RoomMapper roomMapper;

    @Override
    public Booking requestToBooking(UpsertBookingRequest request) {
        Booking booking = new Booking();
        booking.setIn(request.getIn());
        booking.setOut(request.getOut());
        booking.setUser(userService.findById(request.getUserId()));
        booking.setRoom(roomService.findById(request.getRoomId()));

        return booking;
    }

    @Override
    public Booking requestToBooking(UpsertBookingRequest request, Long bookingId) {
        Booking booking = requestToBooking(request);
        booking.setId(bookingId);

        return booking;
    }

    @Override
    public BookingResponse bookingToResponse(Booking booking) {
        BookingResponse response = new BookingResponse();
        response.setId(booking.getId());
        response.setIn(booking.getIn());
        response.setOut(booking.getOut());
        response.setUser(userMapper.userToResponse(booking.getUser()));
        response.setRoom(roomMapper.roomToResponseWithoutHostel(booking.getRoom()));

        return response;
    }

    @Override
    public BookingResponseWithoutRoom bookingToResponseWithoutRoom(Booking booking) {
        BookingResponseWithoutRoom response = new BookingResponseWithoutRoom();
        response.setId(booking.getId());
        response.setIn(booking.getIn());
        response.setOut(booking.getOut());
        response.setUser(userMapper.userToResponse(booking.getUser()));

        return response;
    }

    @Override
    public BookingResponseWithoutUser bookingToResponseWithoutUser(Booking booking) {
        BookingResponseWithoutUser response = new BookingResponseWithoutUser();
        response.setId(booking.getId());
        response.setIn(booking.getIn());
        response.setOut(booking.getOut());
        response.setRoom(roomMapper.roomToResponseWithoutHostel(booking.getRoom()));

        return response;
    }

}
