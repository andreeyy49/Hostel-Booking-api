package org.example.hostelbooking.web.dto.booking;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.example.hostelbooking.web.dto.room.RoomResponseWithoutBooking;
import org.example.hostelbooking.web.dto.user.UserResponseWithoutBooking;

import java.time.Instant;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class BookingResponse {

    private Long id;

    private Instant in;

    private Instant out;

    private RoomResponseWithoutBooking room;

    private UserResponseWithoutBooking user;
}
