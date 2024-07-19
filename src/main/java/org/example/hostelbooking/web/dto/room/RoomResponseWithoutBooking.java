package org.example.hostelbooking.web.dto.room;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.example.hostelbooking.web.dto.hostel.HostelResponseWithoutRooms;

import java.time.Instant;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RoomResponseWithoutBooking {

    private Long id;

    private String name;

    private String description;

    private String number;

    private Float price;

    private Integer maxPeopleSize;

    private Instant bookingTime;

    private HostelResponseWithoutRooms hostel;
}
