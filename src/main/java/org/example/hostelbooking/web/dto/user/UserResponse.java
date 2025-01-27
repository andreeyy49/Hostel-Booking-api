package org.example.hostelbooking.web.dto.user;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.example.hostelbooking.web.dto.booking.BookingResponseWithoutUser;

import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserResponse {

    private Long id;

    private String username;

    private String password;

    private String email;

    private List<BookingResponseWithoutUser> bookings = new ArrayList<>();

}
