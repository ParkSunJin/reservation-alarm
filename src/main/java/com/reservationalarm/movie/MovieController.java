package com.reservationalarm.movie;

import com.reservationalarm.movie.domain.Movie;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.text.ParseException;
import java.util.List;

@RestController
public class MovieController {
    @Autowired
    MovieBO movieBO;

    @GetMapping("/movie")
    public ResponseEntity<List<Movie>> findAllMovie(){
        List<Movie> movies = movieBO.findAllMovie();
        return ResponseEntity.ok().body(movies);
    }

//    @GetMapping("/movie/{movieId}")
//    public  ResponseEntity<Movie> findMovieById(@PathVariable Integer movieId){
//        Movie movie = movieBO.findMovieById(movieId);
//        return ResponseEntity.ok().body(movie);
//    }

    @GetMapping("/movie/{movieTitle}")
    public  ResponseEntity<List<Movie>> findMovieByTitle(@PathVariable String movieTitle){
        List<Movie> movies = movieBO.findMovieByTitle(movieTitle);
        return ResponseEntity.ok().body(movies);
    }

    @PostMapping("/movie")
    public ResponseEntity<Integer> insertMovieByCrawling() throws IOException, ParseException {
        movieBO.insertMovieByCrawling();
        return ResponseEntity.ok().body(200);
    }
}
