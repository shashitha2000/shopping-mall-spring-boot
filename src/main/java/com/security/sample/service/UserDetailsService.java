package com.security.sample.service;

import com.security.sample.dto.UserProfileDto;
import com.security.sample.entity.User;
import com.security.sample.entity.UserDetails;
import com.security.sample.exception.NotFoundException;
import com.security.sample.repository.UserDetailsRepo;
import com.security.sample.repository.UserRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserDetailsService {
    @Autowired
    private UserDetailsRepo userDetailsRepo;
    @Autowired
    private UserRepository userRepository;

    public String userProfile(UserDetails userDetails, int id) {
        UserDetails userDetails1 = new UserDetails();

        userDetails1.setAddress(userDetails.getAddress());
        userDetails1.setContact(userDetails.getContact());
        userDetails1.setImage(userDetails.getImage());
        userDetails1.setUserId(id);


        if (!userDetailsRepo.existsById(id)) {

            userDetailsRepo.save(userDetails1);
            return userDetails1.getId() + " saved";
        } else {
            return "program id already exists";
        }
    }

    //UPDATE USER PROFILE
    public String profileUpdate(UserProfileDto userProfileDto, int id) {


            Optional<User> optionalUser = userRepository.findById(id);
            if (optionalUser.isPresent()) {
                User user = optionalUser.get();

                user.setEmail(userProfileDto.getEmail());
                user.setFirstName(userProfileDto.getFirstName());
                user.setLastName(userProfileDto.getLastName());

                userRepository.save(user);

            }
                Optional<UserDetails> optionalUserDetails = userDetailsRepo.findByUserId(id);
                if (optionalUserDetails.isPresent()) {
                    UserDetails userDetails = optionalUserDetails.get();
                    userDetails.setAddress(userProfileDto.getAddress());
                    userDetails.setContact(userProfileDto.getContact());
                    userDetails.setImage(userProfileDto.getImage());

                    userDetailsRepo.save(userDetails);
                }

                    return "User profile updated successfully for id: " + id;
                }

    }
