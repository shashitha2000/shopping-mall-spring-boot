package com.security.sample.dto;

import com.security.sample.entity.User;
import com.security.sample.entity.UserDetails;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@Getter
@Setter
public class UserProfileDto {

    private String email;
    private String firstName;
    private String lastName;
    private String address;
    private String contact;
    private String image;

}
