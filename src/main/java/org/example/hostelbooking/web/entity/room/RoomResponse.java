package org.example.hostelbooking.web.entity.room;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.example.hostelbooking.web.entity.booking.BookingResponse;
import org.example.hostelbooking.web.entity.booking.BookingResponseWithoutRoom;
import org.example.hostelbooking.web.entity.hostel.HostelResponse;
import org.example.hostelbooking.web.entity.hostel.HostelResponseWithoutRooms;

import java.time.Instant;
import java.util.ArrayList;
import java.util.Date;
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
