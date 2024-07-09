package org.example.hostelbooking.mapper;

import lombok.RequiredArgsConstructor;
import org.example.hostelbooking.entity.Room;
import org.example.hostelbooking.service.HostelService;
import org.example.hostelbooking.web.entity.room.RoomResponse;
import org.example.hostelbooking.web.entity.room.UpsertRoomRequest;
import org.springframework.beans.factory.annotation.Autowired;

import java.time.Instant;

public abstract class RoomMapperDelegate implements RoomMapper{

    @Autowired
    private HostelService hostelService;

    @Autowired
    private HostelMapper hostelMapper;

    @Override
    public Room requestToRoom(UpsertRoomRequest request) {
        Room room = new Room();
        room.setName(request.getName());
        room.setDescription(request.getDescription());
        room.setPrice(request.getPrice());
        room.setNumber(request.getNumber());
        room.setMaxPeopleSize(request.getMaxPeopleSize());
        room.setBookingTime(Instant.parse(request.getBookingTime()));
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
        roomResponse.setName(room.getName());
        roomResponse.setDescription(room.getDescription());
        roomResponse.setPrice(room.getPrice());
        roomResponse.setNumber(room.getNumber());
        roomResponse.setMaxPeopleSize(room.getMaxPeopleSize());
        roomResponse.setBookingTime(room.getBookingTime());

        roomResponse.setHostel(hostelMapper.hostelToResponse(room.getHostel()));

        return roomResponse;
    }
}
