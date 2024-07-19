package org.example.hostelbooking.web.controller;

import lombok.RequiredArgsConstructor;
import org.example.hostelbooking.entity.Room;
import org.example.hostelbooking.mapper.RoomMapper;
import org.example.hostelbooking.service.RoomService;
import org.example.hostelbooking.web.dto.room.RoomListResponse;
import org.example.hostelbooking.web.dto.room.RoomResponse;
import org.example.hostelbooking.web.dto.room.UpsertRoomRequest;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/room")
public class RoomController {

    private final RoomService roomService;

    private final RoomMapper roomMapper;

    @GetMapping
    public RoomListResponse findAll() {
        return roomMapper.roomListToUserListResponse(roomService.findAll());
    }

    @GetMapping("/{id}")
    public RoomResponse findById(@PathVariable Long id) {
        return roomMapper.roomToResponse(roomService.findById(id));
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    @PreAuthorize("hasAnyRole('ROLE_ADMIN')")
    public RoomResponse create(@RequestBody UpsertRoomRequest request) {
        Room room = roomMapper.requestToRoom(request);
        room = roomService.save(room);
        return roomMapper.roomToResponse(room);
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasAnyRole('ROLE_ADMIN')")
    public RoomResponse update(@PathVariable Long id, @RequestBody UpsertRoomRequest request) {
        Room room = roomMapper.requestToRoom(request);
        room.setId(id);
        room = roomService.update(room);

        return roomMapper.roomToResponse(room);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @PreAuthorize("hasAnyRole('ROLE_ADMIN')")
    public void delete(@PathVariable Long id) {
        roomService.delete(id);
    }
}
