package com.reservationalarm.theater;

import com.reservationalarm.theater.domain.Theater;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface TheaterDAO {
    Theater selectTheaterById(Integer theaterId);
    Theater selectTheaterByName(String theaterName);
    List<Theater> selectAllTheater();
    void insertTheater(Theater theater);
}
