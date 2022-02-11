package com.reservationalarm.reservation;

import com.reservationalarm.reservation.domain.Reservation;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface ReservationDAO {
    Reservation selectById(Integer reservationId);
    List<Reservation> selectAll();
    void insert(Reservation reservation);
}

