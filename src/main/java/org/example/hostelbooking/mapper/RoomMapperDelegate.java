package org.example.hostelbooking.mapper;

import org.example.hostelbooking.entity.Booking;
import org.example.hostelbooking.entity.Room;
import org.example.hostelbooking.service.HostelService;
import org.example.hostelbooking.web.entity.booking.BookingResponseWithoutRoom;
import org.example.hostelbooking.web.entity.room.RoomResponse;
import org.example.hostelbooking.web.entity.room.RoomResponseWithoutBooking;
import org.example.hostelbooking.web.entity.room.RoomResponseWithoutHostel;
import org.example.hostelbooking.web.entity.room.UpsertRoomRequest;
import org.springframework.beans.factory.annotation.Autowired;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

public abstract class RoomMapperDelegate implements RoomMapper {

    @Autowired
    private HostelService hostelService;

    @Autowired
    private HostelMapper hostelMapper;

    @Autowired
    private BookingMapper bookingMapper;

    @Override
    public Room requestToRoom(UpsertRoomRequest request) {
        Room room = new Room();
        room.setName(request.getName());
        room.setDescription(request.getDescription());
        room.setPrice(request.getPrice());
        room.setNumber(request.getNumber());
        room.setMaxPeopleSize(request.getMaxPeopleSize());
        if (request.getBookingTime() != null) {
            room.setBookingTime(Instant.parse(request.getBookingTime()));
        }
        room.setHostel(hostelService.findById(Long.valueOf(request.getHostelId())));

        return room;
    }

    @Override
    public Room requestToRoom(UpsertRoomRequest request, Long roomId) {
        Room room = requestToRoom(request);
        room.setId(roomId);

        return room;
    }

    @Override
    public RoomResponse roomToResponse(Room room) {
        RoomResponse roomResponse = new RoomResponse();
        roomResponse.setId(room.getId());
        roomResponse.setName(room.getName());
        roomResponse.setDescription(room.getDescription());
        roomResponse.setPrice(room.getPrice());
        roomResponse.setNumber(room.getNumber());
        roomResponse.setMaxPeopleSize(room.getMaxPeopleSize());
        roomResponse.setBookingTime(room.getBookingTime());

        List<Booking> bookingList = room.getBookings();
        List<BookingResponseWithoutRoom> bookingWithoutRoomList = new ArrayList<>();

        if(bookingList != null) {
            for (Booking booking : bookingList) {
                bookingWithoutRoomList.add(bookingMapper.bookingToResponseWithoutRoom(booking));
            }

            roomResponse.setBookings(bookingWithoutRoomList);
        }

        roomResponse.setHostel(hostelMapper.hostelToResponseWithoutRooms(room.getHostel()));

        return roomResponse;
    }

    @Override
    public RoomResponseWithoutHostel roomToResponseWithoutHostel(Room room) {
        RoomResponseWithoutHostel roomResponse = new RoomResponseWithoutHostel();
        roomResponse.setId(room.getId());
        roomResponse.setName(room.getName());
        roomResponse.setDescription(room.getDescription());
        roomResponse.setPrice(room.getPrice());
        roomResponse.setNumber(room.getNumber());
        roomResponse.setMaxPeopleSize(room.getMaxPeopleSize());
        roomResponse.setBookingTime(room.getBookingTime());

        List<Booking> bookingList = room.getBookings();
        List<BookingResponseWithoutRoom> bookingWithoutRoomList = new ArrayList<>();

        if(bookingList != null) {
            for (Booking booking : bookingList) {
                bookingWithoutRoomList.add(bookingMapper.bookingToResponseWithoutRoom(booking));
            }

            roomResponse.setBookings(bookingWithoutRoomList);
        }

        return roomResponse;
    }

    @Override
    public RoomResponseWithoutBooking roomToResponseWithoutBooking(Room room) {
        RoomResponseWithoutBooking roomResponse = new RoomResponseWithoutBooking();
        roomResponse.setId(room.getId());
        roomResponse.setName(room.getName());
        roomResponse.setDescription(room.getDescription());
        roomResponse.setPrice(room.getPrice());
        roomResponse.setNumber(room.getNumber());
        roomResponse.setMaxPeopleSize(room.getMaxPeopleSize());
        roomResponse.setBookingTime(room.getBookingTime());

        roomResponse.setHostel(hostelMapper.hostelToResponseWithoutRooms(room.getHostel()));

        return roomResponse;
    }
}
