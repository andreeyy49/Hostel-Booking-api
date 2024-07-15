package org.example.hostelbooking.service;

import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.example.hostelbooking.entity.Role;
import org.example.hostelbooking.entity.User;
import org.example.hostelbooking.repository.UserRepository;
import org.example.hostelbooking.utils.BeanUtils;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public List<User> findAll() {
        return userRepository.findAll();
    }

    public User findById(Long id) {
        return userRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("User with id " + id + " not found"));
    }

    public User findByUsername(String username) {
        return userRepository.findByUsername(username)
                .orElse(null);
    }

    public User findByEmail(String email) {
        return userRepository.findByEmail(email)
                .orElse(null);
    }

    public User save(User user, Role role) {

        User byUsername = findByUsername(user.getUsername());
        User byEmail = findByEmail(user.getEmail());

        if (byUsername != null) {
            throw new EntityNotFoundException("User with username " + user.getUsername() + " exist");
        }

        if(byEmail != null) {
            throw new EntityNotFoundException("User with email " + user.getEmail() + " exist");
        }

        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user.setRoles(Collections.singletonList(role));
        role.setUser(user);

        return userRepository.save(user);
    }

    public User update(User user) {
        User existingUser = findById(user.getId());

        BeanUtils.copyNotNullProperties(user, existingUser);

        return userRepository.save(user);
    }

    public void delete(Long id) {
        userRepository.deleteById(id);
    }
}
