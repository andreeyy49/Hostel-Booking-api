package org.example.hostelbooking.mapper;

import org.example.hostelbooking.entity.Room;
import org.example.hostelbooking.web.dto.room.*;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;

import java.util.List;
import java.util.stream.Collectors;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE, uses = BookingMapper.class)
public interface RoomMapper {

    Room requestToRoom(UpsertRoomRequest request);

    @Mapping(target = "id", source = "roomId")
    Room requestToRoom(UpsertRoomRequest request, Long roomId);

    RoomResponse roomToResponse(Room room);

    RoomResponseWithoutHostel roomToResponseWithoutHostel(Room room);

    RoomResponseWithoutBooking roomToResponseWithoutBooking(Room room);

    default RoomListResponse roomListToUserListResponse(List<Room> rooms){
        RoomListResponse response = new RoomListResponse();

        response.setRooms(rooms.stream()
                .map(this::roomToResponse)
                .collect(Collectors.toList()));

        return response;
    }
}
