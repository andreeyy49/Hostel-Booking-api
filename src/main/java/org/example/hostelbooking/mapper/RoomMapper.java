package org.example.hostelbooking.mapper;

import lombok.NoArgsConstructor;
import org.example.hostelbooking.entity.Room;
import org.example.hostelbooking.web.entity.room.RoomListResponse;
import org.example.hostelbooking.web.entity.room.RoomResponse;
import org.example.hostelbooking.web.entity.room.UpsertRoomRequest;
import org.mapstruct.DecoratedWith;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;

import java.util.List;
import java.util.stream.Collectors;

@DecoratedWith(RoomMapperDelegate.class)
@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface RoomMapper {

    Room requestToRoom(UpsertRoomRequest request);

    @Mapping(target = "id", source = "roomId")
    Room requestToRoom(UpsertRoomRequest request, Long roomId);

    RoomResponse roomToResponse(Room room);

    default RoomListResponse roomListToUserListResponse(List<Room> rooms){
        RoomListResponse response = new RoomListResponse();

        response.setRooms(rooms.stream()
                .map(this::roomToResponse)
                .collect(Collectors.toList()));

        return response;
    }
}
