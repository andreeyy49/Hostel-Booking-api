package org.example.hostelbooking.web.dto.booking;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.example.hostelbooking.web.dto.room.RoomResponseWithoutBooking;

import java.time.Instant;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BookingResponseWithoutUser {

    private Long id;

    private Instant in;

    private Instant out;

    private RoomResponseWithoutBooking room;

}
