package com.reservationalarm.reservation;

import com.reservationalarm.reservation.domain.Reservation;
import com.reservationalarm.reservation.model.MovieTime;
import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.ArrayList;

public class ReservationBO {
    ArrayList<MovieTime> findReservation(Reservation reservation) throws IOException {
        String crawlingURL = "http://www.cgv.co.kr/common/showtimes/iframeTheater.aspx?areacode="
                + reservation.getAreaCode()
                + "&theatercode=" + reservation.getTheaterCode()
                + "&date=" + reservation.getDesiredDate();

        Connection conn = Jsoup.connect(crawlingURL);
        Document document = conn.get();

        ArrayList<MovieTime> movieTimes= new ArrayList<MovieTime>();

        // 영화별 상영관 스케줄들 파싱
        Elements showTimeTablesForMovies = document.getElementsByClass("col-times");
        for(Element showTimeTablesForMovie : showTimeTablesForMovies){

            String movieTitle = showTimeTablesForMovie.select(".info-movie a").text();
            if(movieTitle.contains(reservation.getMovie())){
                Elements showTimeTablesForHallTypes = showTimeTablesForMovie.getElementsByClass("type-hall");
                for(Element showTimeTablesForHallType : showTimeTablesForHallTypes){

                    // 상영관 정보 파싱
                    Elements infoHall = showTimeTablesForHallType.select(".info-hall li");
                    String hallType = infoHall.get(0).text();
                    String hallName = infoHall.get(1).text();

                    if(hallType.contains(reservation.getHallType())){
                        Elements infoTimeTables = showTimeTablesForHallType.select(".info-timetable li");
                        for(Element availableTime : infoTimeTables){
                            String time = availableTime.getElementsByTag("em").text();
                            String availableSeats = availableTime.getElementsByTag("span").text();

                            if(availableSeats != "매진"){
                                movieTimes.add(MovieTime.builder()
                                        .movieTitle(movieTitle)
                                        .hallType(hallType)
                                        .hallName(hallName)
                                        .time(time)
                                        .availableSeats(availableSeats)
                                        .build());

                            }
                        }

                    }
                }
            }
        }
        return movieTimes;
    }
}
