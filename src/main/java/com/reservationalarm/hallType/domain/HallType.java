package com.reservationalarm.hallType.domain;

/*
* TODO : enum 공부!
* */
public enum HallType {
    _4DX("4DX"), _2D("2D"), IMAX("IMAX");

    private final String value;
    HallType(String value) {
        this.value = value;
    }

    public String getValue() {
        return this.value;
    }
}
