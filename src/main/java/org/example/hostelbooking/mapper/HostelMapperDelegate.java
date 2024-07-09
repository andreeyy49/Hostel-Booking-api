package org.example.hostelbooking.mapper;

import lombok.RequiredArgsConstructor;
import org.example.hostelbooking.entity.Hostel;
import org.example.hostelbooking.entity.Room;
import org.example.hostelbooking.service.RoomService;
import org.example.hostelbooking.web.entity.hostel.HostelResponse;
import org.example.hostelbooking.web.entity.hostel.UpsertHostelRequest;
import org.example.hostelbooking.web.entity.room.RoomResponse;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;

public abstract class HostelMapperDelegate implements HostelMapper {

    @Autowired
    private RoomService roomService;

    @Autowired

    private RoomMapper roomMapper;

    @Override
    public Hostel requestToHostel(UpsertHostelRequest request) {
        Hostel hostel = new Hostel();
        hostel.setName(request.getName());
        hostel.setAddress(request.getAddress());
        hostel.setCity(request.getCity());
        hostel.setTitle(request.getTitle());
        hostel.setDistance(request.getDistance());

        if (request.getRoomsIds() != null) {
            List<Room> rooms = new ArrayList<>();

            for (String roomId : request.getRoomsIds()) {
                rooms.add(roomService.findById(Long.valueOf(roomId)));
            }

            hostel.setRooms(rooms);
        }

        return hostel;
    }

    @Override
    public Hostel requestToHostel(UpsertHostelRequest request, Long hostelId) {
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

        List<RoomResponse> rooms = new ArrayList<>();
        for (Room room : hostel.getRooms()) {
            rooms.add(roomMapper.roomToResponse(room));
        }

        response.setRooms(rooms);


        return response;
    }

    @Override
    public HostelResponse hostelToResponseWithRating(Hostel hostel) {
        HostelResponse response = hostelToResponse(hostel);
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
