package com.reservationalarm.movie;

import com.reservationalarm.movie.domain.Movie;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/*
 *  TODO: 프론트에서 정확한 movieTitle 보내도록 할것
 * */

@RestController
public class MovieController {
    @Autowired
    MovieBO movieBO;

    @GetMapping("/movie")
    public ResponseEntity<List<Movie>> findAllMovie(){
        List<Movie> movies = movieBO.findAllMovie();
        return ResponseEntity.ok().body(movies);
    }

    @GetMapping("/movie/{movieTitle}")
    public  ResponseEntity<Movie> findMovieByTitle(@PathVariable String movieTitle){
        Movie movies = movieBO.findMovieByTitle(movieTitle);
        return ResponseEntity.ok().body(movies);
    }
}
