package com.security.sample.service;


import com.security.sample.dto.CinemaDto;
import com.security.sample.entity.Movie;
import com.security.sample.repository.MovieBookingRepo;
import com.security.sample.repository.MovieRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class MovieService {
    @Autowired
    private MovieRepo movieRepo;
    @Autowired
    private MovieBookingRepo movieBookingRepo;

    public Movie postMovie(Movie movie) {
        return movieRepo.save(movie);

    }

    //update
    public String updateMovie(CinemaDto cinemaDto, long id) {
        try {
            if (movieRepo.existsById(id)) {
                movieRepo.updateMovie(
                        cinemaDto.getFilmName(),
                        cinemaDto.getDescription(),
                        cinemaDto.getShowTime(),
                        cinemaDto.getImg(),
                        cinemaDto.getDuration(),
                        cinemaDto.getBookedSeats(),
                        cinemaDto.getDate(),
                        cinemaDto.getTime(),
                        id
                );
                return "Movie has been updated with id: " + id;
            } else {
                return "Movie does not exist for this id...";
            }
        } catch (Exception e) {
            return "An error occurred while updating the movie: " + e.getMessage();
        }
    }

    //delete movie
    public String deleteMovie(long id) {
        if (movieRepo.existsById(id)) {
            movieRepo.deleteById(id);
            return "Movie with id " + id + " has been deleted successfully.";
        } else {
            return "Movie with id " + id + " does not exist.";
        }
    }

    //get ALll movies
    public List<Movie> getAllMovies() {

        return movieRepo.findAll();
    }

    //get movie by name
    public List<Movie> searchMoviesByName(String name) {
        return movieRepo.findByFilmNameContainingIgnoreCase(name);
    }

    //get movie by id
    public Movie GetMoviebyId(long id) {
        Optional<Movie> movie = movieRepo.findById(id);
        return movie.orElse(null);
    }
}