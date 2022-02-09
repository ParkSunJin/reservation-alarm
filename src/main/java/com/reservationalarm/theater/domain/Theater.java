package com.reservationalarm.theater.domain;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.Objects;

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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Theater theater = (Theater) o;
        return Objects.equals(areaName, theater.areaName) && Objects.equals(theaterName, theater.theaterName) && Objects.equals(areaCode, theater.areaCode) && Objects.equals(theaterCode, theater.theaterCode) && Objects.equals(theaterNameEng, theater.theaterNameEng) && Objects.equals(areaNameEng, theater.areaNameEng);
    }
}
