package com.security.sample.service;

import com.security.sample.dto.request.RefreshTokenRequestDto;
import com.security.sample.dto.request.SignInRequestDto;
import com.security.sample.dto.request.SignUpRequestDto;
import com.security.sample.dto.response.JwtAuthenticationResponseDto;
import com.security.sample.entity.User;

public interface AuthenticationService {

    User signUp(SignUpRequestDto signUpRequestDto);

    JwtAuthenticationResponseDto signIn(SignInRequestDto signInRequestDto);

    JwtAuthenticationResponseDto refreshToken(RefreshTokenRequestDto refreshTokenRequestDto);
}
