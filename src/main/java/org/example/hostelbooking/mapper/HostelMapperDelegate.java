package org.example.hostelbooking.mapper;

import org.example.hostelbooking.entity.Hostel;
import org.example.hostelbooking.web.entity.hostel.HostelResponse;
import org.example.hostelbooking.web.entity.hostel.UpsertHostelRequest;

import java.util.ArrayList;
import java.util.List;

public abstract class HostelMapperDelegate implements HostelMapper {

    @Override
    public Hostel requestToHostel(UpsertHostelRequest request) {
        Hostel hostel = new Hostel();
        hostel.setName(request.getName());
        hostel.setAddress(request.getAddress());
        hostel.setCity(request.getCity());
        hostel.setTitle(request.getTitle());
        hostel.setDistance(request.getDistance());

        return hostel;
    }

    @Override
    public Hostel requestToHostel(UpsertHostelRequest request, String hostelId) {
        Hostel hostel = requestToHostel(request);
        hostel.setId(hostelId);

        return hostel;
    }

    @Override
    public HostelResponse hostelToResponse(Hostel hostel) {
        HostelResponse response = new HostelResponse();
        response.setId(hostel.getId());
        response.setName(hostel.getName());
        response.setAddress(hostel.getAddress());
        response.setCity(hostel.getCity());
        response.setTitle(hostel.getTitle());
        response.setDistance(hostel.getDistance());

        return response;
    }

    @Override
    public HostelResponse hostelToResponseWithRating(Hostel hostel) {
        HostelResponse response = hostelToResponse(hostel);
        response.setRating(hostel.getRating());
        response.setRatingCounter(hostel.getRatingCounter());

        return response;
    }

//    @Override
//    public List<HostelResponse> hostelListToResponseList(List<Hostel> hostels) {
//        List<HostelResponse>list = new ArrayList<>();
//        for(Hostel hostel : hostels) {
//            list.add(hostelToResponseWithRating(hostel));
//        }
//
//        return list;
//    }
}
