package org.example.hostelbooking.web.controller;

import lombok.RequiredArgsConstructor;
import org.example.hostelbooking.entity.RegistrationData;
import org.example.hostelbooking.entity.Role;
import org.example.hostelbooking.entity.RoleType;
import org.example.hostelbooking.entity.User;
import org.example.hostelbooking.mapper.UserMapper;
import org.example.hostelbooking.service.UserService;
import org.example.hostelbooking.web.dto.user.UpsertUserRequest;
import org.example.hostelbooking.web.dto.user.UserListResponse;
import org.example.hostelbooking.web.dto.user.UserResponse;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/user")
@RequiredArgsConstructor
public class UserController {

    @Value("${app.kafka.registrationMessageTopic}")
    private String topicName;

    private final KafkaTemplate<String, RegistrationData> kafkaTemplate;

    private final UserService userService;

    private final UserMapper userMapper;

    @GetMapping
    public UserListResponse findAll() {
        return userMapper.userListToUserListResponse(userService.findAll());
    }

    @GetMapping("/{id}")
    public UserResponse findById(@PathVariable Long id) {
        return userMapper.userToResponse(userService.findById(id));
    }

    @PostMapping("/account")
    @ResponseStatus(HttpStatus.CREATED)
    public UserResponse create(@RequestBody UpsertUserRequest request,
                                               @RequestParam(name = "roleType") RoleType roleType) {
        User user = userMapper.requestToUser(request);
        user = userService.save(user, Role.from(roleType));

        RegistrationData registrationData = new RegistrationData();
        registrationData.setUserId(user.getId());

        kafkaTemplate.send(topicName, registrationData);

        return userMapper.userToResponse(user);
    }

    @PutMapping("/{id}")
    public UserResponse update(@PathVariable Long id, @RequestBody UpsertUserRequest request) {
        User user = userMapper.requestToUser(request);
        user.setId(id);
        user = userService.update(user);

        return userMapper.userToResponse(user);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Long id) {
        userService.delete(id);
    }
}
