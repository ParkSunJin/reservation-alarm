package com.reservationalarm.reservation.domain;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
public class Reservation {
    public String areaCode;
    public String theaterCode;
    public String desiredDate;
    public String movie;
    public String hallType;
}
