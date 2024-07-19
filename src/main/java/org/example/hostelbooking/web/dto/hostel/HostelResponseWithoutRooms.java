package org.example.hostelbooking.web.dto.hostel;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class HostelResponseWithoutRooms {

    private Long id;

    private String name;

    private String title;

    private String city;

    private String address;

    private Float distance;

    private Float rating;

    private Float ratingCounter;

}
