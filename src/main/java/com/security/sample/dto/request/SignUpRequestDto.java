package com.security.sample.dto.request;

import com.security.sample.entity.Role;
import lombok.Data;

@Data

public class
SignUpRequestDto {

    private String firstName;

    private String lastName;

    private String email;

    private String password;
    private Role role;
}
