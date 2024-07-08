package org.example.hostelbooking.web.controller;

import lombok.RequiredArgsConstructor;
import org.example.hostelbooking.mapper.HostelMapper;
import org.example.hostelbooking.service.HostelService;
import org.example.hostelbooking.web.entity.hostel.HostelListResponse;
import org.example.hostelbooking.web.entity.hostel.HostelResponse;
import org.example.hostelbooking.web.entity.hostel.UpsertHostelRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/hostel")
public class HostelController {

    private final HostelService hostelService;

    private final HostelMapper hostelMapper;

    @GetMapping
    public ResponseEntity<HostelListResponse> findAll() {
        return ResponseEntity.ok().body(hostelMapper.hostelListToUserListResponse(hostelService.findAll()));
    }

    @GetMapping("/{id}")
    public ResponseEntity<HostelResponse> findById(@PathVariable String id) {
        return ResponseEntity.ok().body(hostelMapper.hostelToResponse(hostelService.findById(id)));
    }

    @PostMapping
    public ResponseEntity<HostelResponse> create(@RequestBody UpsertHostelRequest request) {
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(hostelMapper.hostelToResponse(hostelService.save(hostelMapper.requestToHostel(request))));
    }

    @PutMapping("/{id}")
    public ResponseEntity<HostelResponse> update(@PathVariable String id, @RequestBody UpsertHostelRequest request) {
        return ResponseEntity.ok().body(hostelMapper.hostelToResponse(hostelService.update(hostelMapper.requestToHostel(request), id)));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable String id) {
        hostelService.delete(id);

        return ResponseEntity.noContent().build();
    }
}
