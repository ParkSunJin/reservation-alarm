package com.reservationalarm.movie.domain;

import lombok.*;

import java.time.LocalDateTime;
import java.util.Date;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Movie {
    public Integer movieId;
    public String movieTitle;
    public String movieImageSrc;
    public Double movieScore;
    public LocalDateTime openingDate;
    public String detailViewURL;
    public Boolean isOpened;
}
