package com.security.sample.repository;

import com.security.sample.entity.UserDetails;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserDetailsRepo extends JpaRepository<UserDetails, Integer> {


    Optional<UserDetails> findByUserId(int id);
}
