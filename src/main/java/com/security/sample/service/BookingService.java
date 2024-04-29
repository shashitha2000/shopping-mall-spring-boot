package com.security.sample.service;

import com.security.sample.dto.MovieBookingDto;
import com.security.sample.entity.CinemaBooking;
import com.security.sample.entity.Movie;
import com.security.sample.entity.Payment;
import com.security.sample.entity.PaymentStatus;
import com.security.sample.repository.MovieBookingRepo;
import com.security.sample.repository.MovieRepo;
import com.security.sample.repository.PaymentRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class BookingService {
    @Autowired
    private PaymentRepo paymentRepo;
    @Autowired
    private MovieBookingRepo movieBookingRepo;
    @Autowired
    private MovieRepo movieRepo;


    public void saveBooking(MovieBookingDto movieBookingDto, long userId) {

        CinemaBooking cinemaBooking=new CinemaBooking();

        cinemaBooking.setUserId(userId);
        cinemaBooking.setDate(movieBookingDto.getDate());
        cinemaBooking.setTime(movieBookingDto.getTime());
        cinemaBooking.setNoOfSeats(movieBookingDto.getNoOfSeats());
        cinemaBooking.setMovieId(movieBookingDto.getMovieId());
        movieBookingRepo.save(cinemaBooking);

        Movie movie = movieRepo.findById(movieBookingDto.getMovieId()).orElse(null);
        if (movie != null) {
            int newAvailableSeats = movie.getSeats() - movieBookingDto.getNoOfSeats();
            if (newAvailableSeats >= 0) {
                movie.setSeats(newAvailableSeats);
                movieRepo.save(movie);
            }else {
                throw new IllegalArgumentException("Booking exceeds available seats for the movie");
            }
        } else {
            throw new IllegalArgumentException("Movie not found with ID: " + movieBookingDto.getMovieId());
        }

        Payment payment = new Payment();
        payment.setUserId(userId);
        payment.setPaymentStatus(PaymentStatus.PENDING);

        paymentRepo.save(payment);
    }

    }






