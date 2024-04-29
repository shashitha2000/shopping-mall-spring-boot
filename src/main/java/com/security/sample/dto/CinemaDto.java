package com.security.sample.dto;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class CinemaDto {

    private String filmName;
    private String description;
    private String showTime;
    private String img;
    private String duration;
    private int bookedSeats;
    private String date;
    private String time;
}

