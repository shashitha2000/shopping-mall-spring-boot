package com.security.sample.service;

import com.security.sample.entity.User;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.List;

public interface UserService {

    UserDetailsService userDetailsService();


    List<User> getAllUsers();

    void deleteUserById(int userId);
}
