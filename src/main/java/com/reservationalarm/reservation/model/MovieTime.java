package com.reservationalarm.reservation.model;

import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class MovieTime {
    public String movieTitle;
    public String hallType;
    public String hallName;
    public String time;
    public String availableSeats;
}

