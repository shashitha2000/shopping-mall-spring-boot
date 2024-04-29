package com.security.sample.service.serviceImpl;

import com.security.sample.dto.request.RefreshTokenRequestDto;
import com.security.sample.dto.request.SignInRequestDto;
import com.security.sample.dto.request.SignUpRequestDto;
import com.security.sample.dto.response.JwtAuthenticationResponseDto;
import com.security.sample.entity.Role;
import com.security.sample.entity.User;
import com.security.sample.repository.UserRepository;
import com.security.sample.security.JWTService;
import com.security.sample.service.AuthenticationService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashMap;

@Service
@RequiredArgsConstructor
public class AuthenticationServiceImpl implements AuthenticationService {

    private final UserRepository userRepository;

    private final PasswordEncoder passwordEncoder;

    private final AuthenticationManager authenticationManager;

    private final JWTService jwtService;

    public User signUp(SignUpRequestDto signUpRequestDto) {
        User user = new User();

        user.setEmail(signUpRequestDto.getEmail());
        user.setFirstName(signUpRequestDto.getFirstName());
        user.setLastName(signUpRequestDto.getLastName());
        user.setPassword(passwordEncoder.encode(signUpRequestDto.getPassword()));
        user.setRole(signUpRequestDto.getRole());

        return userRepository.save(user);
    }

    public JwtAuthenticationResponseDto signIn(SignInRequestDto signInRequestDto) {
        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(signInRequestDto.getEmail(), signInRequestDto.getPassword()));

        var user = userRepository.findByEmail(signInRequestDto.getEmail()).orElseThrow(() -> new IllegalArgumentException("Invalid Email or Password"));

//        var jwt = jwtService.generateToken(new HashMap<>(),user);
//        var refreshToken = jwtService.generateRefreshToken(new HashMap<>(), user);

        var jwt = jwtService.createToken(user, user.getRole());
        var refreshToken = jwtService.createRefreshToken(user, user.getRole());

        JwtAuthenticationResponseDto jwtAuthenticationResponseDto = new JwtAuthenticationResponseDto();
        jwtAuthenticationResponseDto.setToken(jwt);
        jwtAuthenticationResponseDto.setRefreshToken(refreshToken);
        return jwtAuthenticationResponseDto;
    }

    public JwtAuthenticationResponseDto refreshToken(RefreshTokenRequestDto refreshTokenRequestDto){
        String userEmail = jwtService.extractUsername(refreshTokenRequestDto.getToken());
        User user = userRepository.findByEmail(userEmail).orElseThrow();
        if (jwtService.isTokenValid(refreshTokenRequestDto.getToken(),user)){
//            var jwt = jwtService.generateToken(new HashMap<>(),user);
            var jwt = jwtService.createToken(user, user.getRole());

            JwtAuthenticationResponseDto jwtAuthenticationResponseDto = new JwtAuthenticationResponseDto();

            jwtAuthenticationResponseDto.setToken(jwt);
            jwtAuthenticationResponseDto.setRefreshToken(refreshTokenRequestDto.getToken());
            return jwtAuthenticationResponseDto;
        }

        return null;
    }
}
