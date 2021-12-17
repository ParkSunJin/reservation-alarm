package com.reservationalarm.movie.domain;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.Date;

@Getter
@Setter
@Builder
@ToString
public class Movie {
    public Integer movieId;
    public String movieTitle;
    public String movieImageSrc;
    public Double movieScore;
    public Date openingDate;
    public String reservationLink;
    public Boolean isOpened;
}
