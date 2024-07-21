package org.example.hostelbooking.mapper;

import org.example.hostelbooking.entity.Hostel;
import org.example.hostelbooking.entity.Room;
import org.example.hostelbooking.service.HostelService;
import org.example.hostelbooking.web.dto.room.*;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import org.mapstruct.ReportingPolicy;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.stream.Collectors;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE, uses = BookingMapper.class)
public abstract class RoomMapper {

    @Autowired
    protected HostelService hostelService;

    @Mapping(source = "hostelId", target = "hostel", qualifiedByName = "mapHostel")
    public abstract Room requestToRoom(UpsertRoomRequest request);

    @Mapping(target = "id", source = "roomId")
    public abstract Room requestToRoom(UpsertRoomRequest request, Long roomId);

    public abstract RoomResponse roomToResponse(Room room);

    public abstract RoomResponseWithoutHostel roomToResponseWithoutHostel(Room room);

    public abstract RoomResponseWithoutBooking roomToResponseWithoutBooking(Room room);

    public RoomListResponse roomListToUserListResponse(List<Room> rooms){
        RoomListResponse response = new RoomListResponse();

        response.setRooms(rooms.stream()
                .map(this::roomToResponse)
                .collect(Collectors.toList()));

        return response;
    }

    @Named(value = "mapHostel")
    Hostel mapHostel(Long id) {
        return hostelService.findById(id);
    }
}
