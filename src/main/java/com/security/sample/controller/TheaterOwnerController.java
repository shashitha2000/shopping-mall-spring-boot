package com.security.sample.controller;


import com.security.sample.dto.CinemaDto;
import com.security.sample.entity.Movie;
import com.security.sample.service.MovieService;
import com.security.sample.util.StandardResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("/api/v1/cinema")
public class TheaterOwnerController {

    @Autowired
    private MovieService movieService;


//    @GetMapping({"/forUser"})
//    @PreAuthorize("hasRole('CINEMA')")
//    public String forUser(){
//        return "This URL is only accessible to the cinema";
//    }

    //CINEMA
    //create
    //*THEATER_OWNER
    @PostMapping(path = "/post-movie")
    public ResponseEntity<StandardResponse> postMovie(@RequestBody Movie movie){
        Movie name= movieService.postMovie(movie);
        return new ResponseEntity<StandardResponse>(
                new StandardResponse(201,"movie successfully saved",name),
                HttpStatus.CREATED
        );
    }
    //update movie
    //*THEATER_OWNER ROLE
    @PutMapping(path={"/update/{id}"})

    public ResponseEntity<StandardResponse> updateMovie(
            @RequestBody CinemaDto cinemaDto,
            @PathVariable(value = "id") long id) throws ChangeSetPersister.NotFoundException {
        String updated= movieService.updateMovie(cinemaDto,id);

        return new ResponseEntity<StandardResponse>(
                new StandardResponse(201,id+"Movie successfully saved",updated),
                HttpStatus.CREATED
        );
    }

    //delete
    //*THEATER_OWNER ROLE
    @DeleteMapping("/delete-movie/{id}")
    public ResponseEntity<String> deleteMovie(@PathVariable("id") long id) {
        String message = movieService.deleteMovie(id);
        return ResponseEntity.ok(message);
    }

    //get program by id
    @GetMapping(path={"/get-by-id/{id}"})
    public Movie getMovieById(@PathVariable long id)  {
        Movie movie=movieService.GetMoviebyId(id);
        return movie;
    }

}
