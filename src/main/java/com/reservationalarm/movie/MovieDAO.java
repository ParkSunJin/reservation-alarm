package com.reservationalarm.movie;

import com.reservationalarm.movie.domain.Movie;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface MovieDAO {
    Movie selectMovieById(Integer movieId);
    List<Movie> selectMovieByTitle(String movieTitle);
    List<Movie> selectAllMovie();
    void insertMovie(Movie movie);
    void updateMovie(Movie movie);
    void deleteAllMovie();
}
