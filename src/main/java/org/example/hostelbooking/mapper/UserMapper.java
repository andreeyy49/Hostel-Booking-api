package org.example.hostelbooking.mapper;

import org.example.hostelbooking.entity.User;
import org.example.hostelbooking.web.entity.user.UpsertUserRequest;
import org.example.hostelbooking.web.entity.user.UserListResponse;
import org.example.hostelbooking.web.entity.user.UserResponse;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;

import java.util.List;
import java.util.stream.Collectors;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface UserMapper {

    User requestToUser(UpsertUserRequest request);

    @Mapping(target = "id", source = "userId")
    User requestToUser(UpsertUserRequest request, Long userId);

    UserResponse userToResponse(User room);

    default UserListResponse userListToUserListResponse(List<User> users){
        UserListResponse response = new UserListResponse();

        response.setUsers(users.stream()
                .map(this::userToResponse)
                .collect(Collectors.toList()));

        return response;
    }
}
