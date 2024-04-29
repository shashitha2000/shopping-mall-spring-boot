package com.security.sample.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor

public class MovieBookingDto {

    private String date;
    private String time;
    private int noOfSeats;
    private long movieId;

}
