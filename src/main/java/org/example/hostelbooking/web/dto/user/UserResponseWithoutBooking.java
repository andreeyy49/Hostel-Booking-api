package org.example.hostelbooking.web.dto.user;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserResponseWithoutBooking {

    private Long id;

    private String username;

    private String password;

    private String email;

}
