package com.security.sample.dto.response;

import lombok.Data;

@Data
public class JwtAuthenticationResponseDto {

    private String token;

    private String refreshToken;
}
