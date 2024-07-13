package org.example.hostelbooking.web.controller;

import lombok.RequiredArgsConstructor;
import org.example.hostelbooking.entity.RoleType;
import org.example.hostelbooking.entity.User;
import org.example.hostelbooking.mapper.UserMapper;
import org.example.hostelbooking.service.UserService;
import org.example.hostelbooking.web.entity.user.UpsertUserRequest;
import org.example.hostelbooking.web.entity.user.UserListResponse;
import org.example.hostelbooking.web.entity.user.UserResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/user")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    private final UserMapper userMapper;

    @GetMapping
    public ResponseEntity<UserListResponse> findAll() {
        return ResponseEntity.ok().body(userMapper.userListToUserListResponse(userService.findAll()));
    }

    @GetMapping("/{id}")
    public ResponseEntity<UserResponse> findById(@PathVariable Long id) {
        return ResponseEntity.ok().body(userMapper.userToResponse(userService.findById(id)));
    }

    @PostMapping
    public ResponseEntity<UserResponse> create(@RequestBody UpsertUserRequest request,
                                               @RequestParam(name = "roleType") String roleType) {
        User user = userMapper.requestToUser(request);
        user.setRole(roleType);
        user = userService.save(user);

        return ResponseEntity.status(HttpStatus.CREATED).body(userMapper.userToResponse(user));
    }

    @PutMapping("/{id}")
    public ResponseEntity<UserResponse> update(@PathVariable Long id, @RequestBody UpsertUserRequest request) {
        User user = userMapper.requestToUser(request);
        user.setId(id);
        user = userService.update(user);

        return ResponseEntity.ok().body(userMapper.userToResponse(user));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        userService.delete(id);

        return ResponseEntity.noContent().build();
    }
}
