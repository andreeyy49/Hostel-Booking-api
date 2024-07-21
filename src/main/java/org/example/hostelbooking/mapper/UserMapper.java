package org.example.hostelbooking.mapper;

import org.example.hostelbooking.entity.User;
import org.example.hostelbooking.web.dto.user.UpsertUserRequest;
import org.example.hostelbooking.web.dto.user.UserListResponse;
import org.example.hostelbooking.web.dto.user.UserResponse;
import org.example.hostelbooking.web.dto.user.UserResponseWithoutBooking;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;

import java.util.List;
import java.util.stream.Collectors;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE, uses = BookingMapper.class)
public abstract class UserMapper {

    public abstract User requestToUser(UpsertUserRequest request);

    @Mapping(target = "id", source = "userId")
    public abstract User requestToUser(UpsertUserRequest request, Long userId);

    public abstract UserResponse userToResponse(User user);

    public abstract UserResponseWithoutBooking userToResponseWithoutBooking(User user);

    public UserListResponse userListToUserListResponse(List<User> users){
        UserListResponse response = new UserListResponse();

        response.setUsers(users.stream()
                .map(this::userToResponse)
                .collect(Collectors.toList()));

        return response;
    }
}
