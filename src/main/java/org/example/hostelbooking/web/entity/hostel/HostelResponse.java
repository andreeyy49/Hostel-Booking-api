package org.example.hostelbooking.web.entity.hostel;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.example.hostelbooking.entity.Rating;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class HostelResponse {

    private String id;

    private String name;

    private String title;

    private String city;

    private String address;

    private Float distance;

    private Rating rating;

    private Float ratingCounter;

}
