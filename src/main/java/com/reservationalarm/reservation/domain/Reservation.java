package com.reservationalarm.reservation.domain;

import com.reservationalarm.hallType.domain.HallType;
import com.reservationalarm.theater.domain.Theater;
import lombok.*;

import java.util.Date;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Reservation {
    public Integer reservationId;
    public Theater theater;
    public Date desiredDate;
    public String movieTitle;
    public HallType hallType;
}
