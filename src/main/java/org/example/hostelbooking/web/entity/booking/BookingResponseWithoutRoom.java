package org.example.hostelbooking.web.entity.booking;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.example.hostelbooking.web.entity.user.UserResponse;

import java.time.Instant;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BookingResponseWithoutRoom {

    private Long id;

    private Instant in;

    private Instant out;

    private UserResponse user;
}
