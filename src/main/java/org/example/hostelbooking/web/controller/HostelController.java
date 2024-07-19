package org.example.hostelbooking.web.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.example.hostelbooking.entity.Hostel;
import org.example.hostelbooking.mapper.HostelMapper;
import org.example.hostelbooking.service.HostelService;
import org.example.hostelbooking.web.dto.hostel.*;
import org.springframework.http.HttpStatus;
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
    public HostelListResponse findAll() {
        return hostelMapper.hostelListToHostelListResponse(hostelService.findAll());
    }

    @GetMapping("/filter")
    public HostelPaginationResponse filterBy(@RequestBody @Valid HostelFilter filter) {

        List<Hostel> hostels = hostelService.filterBy(filter);

        HostelListResponse listResponse = hostelMapper.hostelListToHostelListResponse(hostels);

        HostelPaginationResponse response = new HostelPaginationResponse();
        response.setHostelList(listResponse);
        response.setSize(hostels.size());

        return response;
    }

    @GetMapping("/{id}")
    public HostelResponse findById(@PathVariable Long id) {
        return hostelMapper.hostelToResponse(hostelService.findById(id));
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    @PreAuthorize("hasAnyRole('ROLE_ADMIN')")
    public HostelResponse create(@RequestBody UpsertHostelRequest request) {
        Hostel hostel = hostelMapper.requestToHostel(request);
        hostel = hostelService.save(hostel);
        return hostelMapper.hostelToResponse(hostel);
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasAnyRole('ROLE_ADMIN')")
    public HostelResponse update(@PathVariable Long id, @RequestBody UpsertHostelRequest request) {
        Hostel updatedHostel = hostelMapper.requestToHostel(request);
        updatedHostel.setId(id);
        updatedHostel = hostelService.update(updatedHostel);

        return hostelMapper.hostelToResponse(updatedHostel);
    }

    @PutMapping("/rating/{id}")
    public HostelResponse updateRating(@PathVariable Long id,
                                                       @RequestBody @Valid UpsertHostelRatingRequest request) {
        return hostelMapper.hostelToResponseWithRating(hostelService.updateRating(id, request.getNewMark()));
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @PreAuthorize("hasAnyRole('ROLE_ADMIN')")
    public void delete(@PathVariable Long id) {
        hostelService.delete(id);
    }
}
