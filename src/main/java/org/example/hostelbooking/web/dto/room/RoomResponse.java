package org.example.hostelbooking.web.dto.room;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.example.hostelbooking.web.dto.booking.BookingResponseWithoutRoom;
import org.example.hostelbooking.web.dto.hostel.HostelResponseWithoutRooms;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

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

    private HostelResponseWithoutRooms hostel;

    private List<BookingResponseWithoutRoom> bookings = new ArrayList<>();
}
