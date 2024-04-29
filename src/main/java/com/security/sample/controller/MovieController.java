package com.security.sample.controller;


import com.security.sample.dto.CinemaBookingPaymentDto;
import com.security.sample.dto.MovieBookingDto;
import com.security.sample.entity.CinemaBooking;
import com.security.sample.entity.Movie;
import com.security.sample.entity.Feedback;
import com.security.sample.entity.Payment;
import com.security.sample.service.BookingService;
import com.security.sample.service.MovieService;
import com.security.sample.service.FeedbackService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("/authenticated-user")

public class MovieController {
    @Autowired
    private MovieService movieService;

    @Autowired
    private BookingService bookingService;

    //--------------------------------------------CINEMA THEATER-------------------------------------
    //get all movies
    @GetMapping("/get-all-movies")
    public ResponseEntity<List<Movie>> getAllMovies() {
        List<Movie> movies = movieService.getAllMovies();
        if (movies.isEmpty()) {
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.ok(movies);
        }
    }


    //get by name
    @GetMapping("/search-by-name/{name}")
    public ResponseEntity<List<Movie>> getByName(@PathVariable String name) {
        List<Movie> movies = movieService.searchMoviesByName(name);

        if (movies.isEmpty()) {
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.ok(movies);
        }
    }


//--------------------------------------------------------------Booking


    @PostMapping(path = "/movie-booking/{userId}")
    public ResponseEntity<String> movieBooking(
            @PathVariable long userId,
            @RequestBody MovieBookingDto movieBookingDto) {
        bookingService.saveBooking(movieBookingDto,userId);
        return ResponseEntity.status(HttpStatus.OK).body("Data saved successfully");
    }

//--------------------------------------------------------------payment


}


