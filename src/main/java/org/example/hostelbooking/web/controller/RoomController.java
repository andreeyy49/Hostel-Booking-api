package org.example.hostelbooking.web.controller;

import lombok.RequiredArgsConstructor;
import org.example.hostelbooking.entity.Room;
import org.example.hostelbooking.mapper.RoomMapper;
import org.example.hostelbooking.service.RoomService;
import org.example.hostelbooking.web.entity.room.RoomListResponse;
import org.example.hostelbooking.web.entity.room.RoomResponse;
import org.example.hostelbooking.web.entity.room.UpsertRoomRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/room")
public class RoomController {

    private final RoomService roomService;

    private final RoomMapper roomMapper;

    @GetMapping
    public ResponseEntity<RoomListResponse> findAll() {
        return ResponseEntity.ok().body(roomMapper.roomListToUserListResponse(roomService.findAll()));
    }

    @GetMapping("/{id}")
    public ResponseEntity<RoomResponse> findById(@PathVariable Long id) {
        return ResponseEntity.ok().body(roomMapper.roomToResponse(roomService.findById(id)));
    }

    @PostMapping
    public ResponseEntity<RoomResponse> create(@RequestBody UpsertRoomRequest request) {
        Room room = roomMapper.requestToRoom(request);
        room = roomService.save(room);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(roomMapper.roomToResponse(room));
    }

    @PutMapping("/{id}")
    public ResponseEntity<RoomResponse> update(@PathVariable Long id, @RequestBody UpsertRoomRequest request) {
        Room room = roomMapper.requestToRoom(request);
        room.setId(id);
        room = roomService.update(room);

        return ResponseEntity.ok().body(roomMapper.roomToResponse(room));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        roomService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
