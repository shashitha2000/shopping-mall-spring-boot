package com.security.sample.controller;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.security.sample.dto.MovieBookingDto;
import com.security.sample.entity.Movie;
import com.security.sample.service.BookingService;
import com.security.sample.service.MovieService;

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
    // Delete movie by ID
@DeleteMapping("/delete-movie/{id}")
public ResponseEntity<String> deleteMovie(@PathVariable long id) {
    movieService.deleteMovie(id);
    return ResponseEntity.ok("Movie with ID " + id + " deleted successfully");
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


