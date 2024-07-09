package org.example.hostelbooking.mapper;

import org.example.hostelbooking.entity.Hostel;
import org.example.hostelbooking.web.entity.hostel.HostelListResponse;
import org.example.hostelbooking.web.entity.hostel.HostelResponse;
import org.example.hostelbooking.web.entity.hostel.UpsertHostelRequest;
import org.mapstruct.DecoratedWith;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;

import java.util.List;
import java.util.stream.Collectors;

@DecoratedWith(HostelMapperDelegate.class)
@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface HostelMapper {

    Hostel requestToHostel(UpsertHostelRequest request);

    @Mapping(source = "hostelId", target = "id")
    Hostel requestToHostel(UpsertHostelRequest request, Long hostelId);

    HostelResponse hostelToResponse(Hostel hostel);

    HostelResponse hostelToResponseWithRating(Hostel hostel);

//    List<HostelResponse> hostelListToResponseList(List<Hostel> hostels);

    default HostelListResponse hostelListToUserListResponse(List<Hostel> hostels){
        HostelListResponse response = new HostelListResponse();

        response.setHostels(hostels.stream()
                .map(this::hostelToResponseWithRating)
                .collect(Collectors.toList()));

        return response;
    }
}
