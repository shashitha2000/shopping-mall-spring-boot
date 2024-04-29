package com.security.sample.dto;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Getter
@Setter
public class CinemaBookingPaymentDto {

    private PaymentDto paymentDto;
    private CinemaBookingDto cinemaBookingDto;
}
