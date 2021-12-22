package com.reservationalarm.theater;

import com.reservationalarm.movie.domain.Movie;
import com.reservationalarm.theater.domain.Theater;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface TheaterDAO {
    Theater selectTheaterById(Integer theaterId);
    List<Theater> selectTheaterByName(String theaterName);
    List<Theater> selectAllTheater();
    void insertTheater(Theater theater);
    void deleteAllTheater();
}
