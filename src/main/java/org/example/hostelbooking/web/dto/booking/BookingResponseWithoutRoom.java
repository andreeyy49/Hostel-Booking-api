package org.example.hostelbooking.web.dto.booking;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.example.hostelbooking.web.dto.user.UserResponseWithoutBooking;

import java.time.Instant;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BookingResponseWithoutRoom {

    private Long id;

    private Instant inBooking;

    private Instant outBooking;

    private UserResponseWithoutBooking user;
}
