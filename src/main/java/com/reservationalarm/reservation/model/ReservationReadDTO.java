package com.reservationalarm.reservation.model;

import com.reservationalarm.hallType.domain.HallType;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Builder
@Getter
@Setter
public class ReservationReadDTO {
    public String theater;
    public Date desiredDate;
    public String movieTitle;
    public HallType hallType;
    public Boolean isPushed;
}
