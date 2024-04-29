package com.security.sample.repository;

import com.security.sample.entity.Movie;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.stereotype.Repository;

import java.util.List;

@Transactional
@Repository
@EnableJpaRepositories

public interface MovieRepo extends JpaRepository<Movie,Long> {
    @Modifying
    @Query(value = "update movie set film_name = ?1, description = ?2, show_time = ?3, img = ?4, duration = ?5, booked_seats = ?6, date = ?7, time = ?8 where id = ?9", nativeQuery = true)
    void updateMovie(String filmName, String description, String showTime, String img, String duration, int bookedSeats, String date, String time, long id);

    List<Movie> findByFilmNameContainingIgnoreCase(String name);

}