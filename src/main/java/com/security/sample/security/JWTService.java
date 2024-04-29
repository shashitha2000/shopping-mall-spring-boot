package com.security.sample.security;

import com.security.sample.entity.Role;
import com.security.sample.entity.User;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.HashMap;
import java.util.Map;

public interface JWTService {

    String extractUsername(String token);

    String generateToken(Map<String, Object> extraClaims, UserDetails userDetails);

    Boolean isTokenValid(String token, UserDetails userDetails);

    String generateRefreshToken(Map<String, Object> extraClaims, UserDetails userDetails);

    String createToken(UserDetails username, Role role);

    String createRefreshToken(UserDetails username, Role role);
}
