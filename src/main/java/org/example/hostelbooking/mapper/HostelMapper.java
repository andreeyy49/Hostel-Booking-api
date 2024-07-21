package org.example.hostelbooking.mapper;

import org.example.hostelbooking.entity.Hostel;
import org.example.hostelbooking.web.dto.hostel.HostelListResponse;
import org.example.hostelbooking.web.dto.hostel.HostelResponse;
import org.example.hostelbooking.web.dto.hostel.HostelResponseWithoutRooms;
import org.example.hostelbooking.web.dto.hostel.UpsertHostelRequest;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;

import java.util.List;
import java.util.stream.Collectors;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE, uses = RoomMapper.class)
public abstract class HostelMapper {

    public abstract Hostel requestToHostel(UpsertHostelRequest request);

    @Mapping(source = "hostelId", target = "id")
    public abstract Hostel requestToHostel(UpsertHostelRequest request, Long hostelId);

    public abstract HostelResponse hostelToResponse(Hostel hostel);

    public abstract HostelResponseWithoutRooms hostelToResponseWithoutRooms(Hostel hostel);

    public abstract HostelResponse hostelToResponseWithRating(Hostel hostel);

    public HostelListResponse hostelListToHostelListResponse(List<Hostel> hostels){
        HostelListResponse response = new HostelListResponse();

        response.setHostels(hostels.stream()
                .map(this::hostelToResponseWithRating)
                .collect(Collectors.toList()));

        return response;
    }
}
