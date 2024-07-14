package org.example.hostelbooking.web.entity.booking;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.example.hostelbooking.web.entity.room.RoomResponseWithoutHostel;
import org.example.hostelbooking.web.entity.user.UserResponse;

import java.time.Instant;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class BookingResponse {

    private Long id;

    private Instant in;

    private Instant out;

    private RoomResponseWithoutHostel room;

    private UserResponse user;
}
