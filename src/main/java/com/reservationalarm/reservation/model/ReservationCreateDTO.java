package com.reservationalarm.reservation.model;

import com.reservationalarm.hallType.domain.HallType;
import lombok.*;

import java.util.Date;

@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ReservationCreateDTO {
    public String theater;
    public Date desiredDate;
    public String movieTitle;
    public HallType hallType;
}
