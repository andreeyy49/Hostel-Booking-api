package org.example.hostelbooking.mapper;

import org.example.hostelbooking.entity.Booking;
import org.example.hostelbooking.entity.User;
import org.example.hostelbooking.web.entity.booking.BookingResponseWithoutUser;
import org.example.hostelbooking.web.entity.user.UpsertUserRequest;
import org.example.hostelbooking.web.entity.user.UserResponse;
import org.example.hostelbooking.web.entity.user.UserResponseWithoutBooking;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;

public abstract class UserMapperDelegate implements UserMapper {

    @Autowired
    private BookingMapper bookingMapper;

    @Override
    public User requestToUser(UpsertUserRequest request) {
        User user = new User();
        user.setUsername(request.getUsername());
        user.setPassword(request.getPassword());
        user.setEmail(request.getEmail());

        return user;
    }

    @Override
    public User requestToUser(UpsertUserRequest request, Long userId) {
        User user = requestToUser(request);
        user.setId(userId);

        return user;
    }

    @Override
    public UserResponse userToResponse(User user) {
        UserResponse response = new UserResponse();
        response.setId(user.getId());
        response.setUsername(user.getUsername());
        response.setPassword(user.getPassword());
        response.setEmail(user.getEmail());
        response.setRole(user.getRole());

        List<Booking> bookings = user.getBookings();
        if(bookings != null) {
            List<BookingResponseWithoutUser> bookingsResponse = new ArrayList<>();

            for(Booking booking : bookings) {
                bookingsResponse.add(bookingMapper.bookingToResponseWithoutUser(booking));
            }

            response.setBookings(bookingsResponse);
        }

        return response;
    }

    @Override
    public UserResponseWithoutBooking userToResponseWithoutBooking(User user) {
        UserResponseWithoutBooking response = new UserResponseWithoutBooking();
        response.setId(user.getId());
        response.setUsername(user.getUsername());
        response.setPassword(user.getPassword());
        response.setEmail(user.getEmail());
        response.setRole(user.getRole());

        return response;
    }
}
