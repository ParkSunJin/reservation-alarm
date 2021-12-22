package com.reservationalarm.theater.domain;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class Theater {
    public Integer theaterId;
    public String areaName;
    public String theaterName;
    public String areaCode;
    public String theaterCode;
    public String theaterNameEng;
    public String areaNameEng;
}
