package com.security.sample.service.serviceImpl;

import com.security.sample.entity.User;
import com.security.sample.repository.UserRepository;
import com.security.sample.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    @Override
    public UserDetailsService userDetailsService(){
        return new UserDetailsService() {
            @Override
            public UserDetails loadUserByUsername(String username)  {
                return userRepository.findByEmail(username)
                        .orElseThrow(() -> new UsernameNotFoundException("Username Not Found"));
            }
        };
    }

    @Override
    public List<User> getAllUsers() {
        List<User> getAllUsers = userRepository.findAll();
        if (!getAllUsers.isEmpty()) {
            return getAllUsers;
        } else {
            return Collections.emptyList();
        }
    }

    @Override
    public void deleteUserById(int userId) {
        Optional<User> userOptional = userRepository.findById(userId);
        if (userOptional.isPresent()) {
            userRepository.deleteById(userId);
        } else {
            throw new IllegalArgumentException("User not found with id: " + userId);
        }
    }
}
