package com.reservationalarm.reservation;

import com.reservationalarm.reservation.domain.Reservation;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface ReservationDAO {
    Reservation selectById(Integer reservationId);
    List<Reservation> selectAll();
    //List<Reservation> selectByTheaterIdAndDate(Integer theaterId, String date);
    void insertReservation(Reservation reservation);
}

