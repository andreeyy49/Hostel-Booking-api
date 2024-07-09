package org.example.hostelbooking.web.entity.room;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.example.hostelbooking.web.entity.hostel.HostelResponse;

import java.time.Instant;
import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RoomResponse {

    private Long id;

    private String name;

    private String description;

    private String number;

    private Float price;

    private Integer maxPeopleSize;

    private Instant bookingTime;

    private HostelResponse hostel;
}
