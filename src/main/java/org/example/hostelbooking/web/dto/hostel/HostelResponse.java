package org.example.hostelbooking.web.dto.hostel;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.example.hostelbooking.web.dto.room.RoomResponseWithoutHostel;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class HostelResponse {

    private Long id;

    private String name;

    private String title;

    private String city;

    private String address;

    private Float distance;

    private Float rating;

    private Float ratingCounter;

    private List<RoomResponseWithoutHostel> rooms;

}
