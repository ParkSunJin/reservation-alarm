package com.reservationalarm.movie;

import com.reservationalarm.movie.domain.Movie;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class MovieBO {
    @Autowired
    MovieDAO movieDAO;

    // 영화 정보 크롤링 url
    private final static String crawlingURL = "http://www.cgv.co.kr/movies/?lt=1&ft=0";

    public Movie findMovieById(Integer movieId) {
        return movieDAO.selectMovieById(movieId);
    }

    public List<Movie> findMovieByTitle(String movieTitle) {
        if (movieTitle.isEmpty()) return null;
        return movieDAO.selectMovieByTitle(movieTitle);
    }

    public List<Movie> findAllMovie() {
        return movieDAO.selectAllMovie();
    }
}
