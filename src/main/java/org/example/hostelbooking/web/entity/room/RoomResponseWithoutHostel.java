package org.example.hostelbooking.web.entity.room;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.example.hostelbooking.web.entity.booking.BookingResponseWithoutRoom;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RoomResponseWithoutHostel {

    private Long id;

    private String name;

    private String description;

    private String number;

    private Float price;

    private Integer maxPeopleSize;

    private Instant bookingTime;

    private List<BookingResponseWithoutRoom> bookings = new ArrayList<>();
}
