package org.example.hostelbooking.web.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.example.hostelbooking.entity.Hostel;
import org.example.hostelbooking.mapper.HostelMapper;
import org.example.hostelbooking.service.HostelService;
import org.example.hostelbooking.web.entity.hostel.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/hostel")
public class HostelController {

    private final HostelService hostelService;

    private final HostelMapper hostelMapper;

    @GetMapping
    public ResponseEntity<HostelListResponse> findAll() {
        return ResponseEntity.ok().body(hostelMapper.hostelListToHostelListResponse(hostelService.findAll()));
    }

    @GetMapping("/filter")
    public ResponseEntity<HostelPaginationResponse> filterBy(@RequestBody @Valid HostelFilter filter) {

        List<Hostel> hostels = hostelService.filterBy(filter);

        HostelListResponse listResponse = hostelMapper.hostelListToHostelListResponse(hostels);

        HostelPaginationResponse response = new HostelPaginationResponse();
        response.setHostelList(listResponse);
        response.setSize(hostels.size());

        return ResponseEntity.ok().body(response);
    }

    @GetMapping("/{id}")
    public ResponseEntity<HostelResponse> findById(@PathVariable Long id) {
        return ResponseEntity.ok().body(hostelMapper.hostelToResponse(hostelService.findById(id)));
    }

    @PostMapping
    @PreAuthorize("hasAnyRole('ROLE_ADMIN')")
    public ResponseEntity<HostelResponse> create(@RequestBody UpsertHostelRequest request) {
        Hostel hostel = hostelMapper.requestToHostel(request);
        hostel = hostelService.save(hostel);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(hostelMapper.hostelToResponse(hostel));
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasAnyRole('ROLE_ADMIN')")
    public ResponseEntity<HostelResponse> update(@PathVariable Long id, @RequestBody UpsertHostelRequest request) {
        Hostel updatedHostel = hostelMapper.requestToHostel(request);
        updatedHostel.setId(id);
        updatedHostel = hostelService.update(updatedHostel);

        return ResponseEntity.ok().body(hostelMapper.hostelToResponse(updatedHostel));
    }

    @PutMapping("/rating/{id}")
    public ResponseEntity<HostelResponse> updateRating(@PathVariable Long id,
                                                       @RequestBody @Valid UpsertHostelRatingRequest request) {
        return ResponseEntity.ok().body(hostelMapper.hostelToResponseWithRating(hostelService.updateRating(id, request.getNewMark())));
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasAnyRole('ROLE_ADMIN')")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        hostelService.delete(id);

        return ResponseEntity.noContent().build();
    }
}
