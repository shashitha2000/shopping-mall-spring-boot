package com.security.sample.repository;

import com.security.sample.dto.UserProfileDto;
import com.security.sample.entity.Role;
import com.security.sample.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Integer> {

    Optional<User> findByEmail(String email);


    User findByRole(Role role);


}
