package org.example.hostelbooking.web.entity.hostel;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.example.hostelbooking.web.entity.room.RoomResponse;

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

    private Integer rating;

    private Float ratingCounter;

    private List<RoomResponse> rooms;

}
