package com.reservationalarm.reservation;

import com.reservationalarm.movie.MovieBO;
import com.reservationalarm.movie.domain.Movie;
import com.reservationalarm.reservation.domain.Reservation;
import com.reservationalarm.reservation.model.ReservationCreateDTO;
import com.reservationalarm.reservation.model.ReservationReadDTO;
import com.reservationalarm.theater.TheaterBO;
import com.reservationalarm.theater.domain.Theater;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ReservationModelConverter {
    private static TheaterBO theaterBO;
    private static MovieBO movieBO;

    @Autowired
    private ReservationModelConverter(TheaterBO theaterBO, MovieBO movieBO){
        this.theaterBO = theaterBO;
        this.movieBO = movieBO;
    }

    public static Reservation of(ReservationCreateDTO dto){
        Theater theater = theaterBO.findTheaterByName(dto.theater);
        Movie movie = movieBO.findMovieByTitle(dto.movieTitle);

        return Reservation.builder()
                .theater(theater)
                .movie(movie)
                .desiredDate(dto.desiredDate)
                .hallType(dto.hallType)
                .isPushed(false)
                .build();
    }

    public static ReservationReadDTO of(Reservation reservation){
        return ReservationReadDTO.builder()
                .theater(reservation.getTheater().theaterName)
                .movieTitle(reservation.getMovie().movieTitle)
                .desiredDate(reservation.desiredDate)
                .hallType(reservation.getHallType())
                .isPushed(reservation.isPushed)
                .build();
    }
}
