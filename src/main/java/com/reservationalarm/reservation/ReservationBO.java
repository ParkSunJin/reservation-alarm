package com.reservationalarm.reservation;

import com.reservationalarm.reservation.domain.Reservation;
import com.reservationalarm.reservation.model.MovieTime;
import com.reservationalarm.reservation.model.ReservationCreateDTO;
import com.reservationalarm.reservation.model.ReservationReadDTO;
import com.reservationalarm.theater.domain.Theater;
import lombok.RequiredArgsConstructor;
import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ReservationBO {
    private final ReservationDAO reservationDAO;

    public void makeReservation(ReservationCreateDTO reservationCreateDTO){
        Reservation reservation = ReservationModelConverter.of(reservationCreateDTO);
        reservationDAO.insert(reservation);
    }

    public List<ReservationReadDTO> findAll(){
        List<Reservation> reservations = reservationDAO.selectAll();
        return reservations.stream()
                .map(ReservationModelConverter::of)
                .collect(Collectors.toList());
    }

    ArrayList<MovieTime> findReservation(Reservation reservation) throws IOException {
        Theater theater = reservation.getTheater();
        String crawlingURL = "http://www.cgv.co.kr/common/showtimes/iframeTheater.aspx?areacode="
                + theater.getAreaCode()
                + "&theatercode=" + theater.getTheaterCode()
                + "&date=" + reservation.getDesiredDate();

        Connection conn = Jsoup.connect(crawlingURL);
        Document document = conn.get();

        ArrayList<MovieTime> movieTimes= new ArrayList<MovieTime>();

        // 영화별 상영관 스케줄들 파싱
        Elements showTimeTablesForMovies = document.getElementsByClass("col-times");
        for(Element showTimeTablesForMovie : showTimeTablesForMovies){

            String movieTitle = showTimeTablesForMovie.select(".info-movie a").text();
            if(movieTitle.contains(reservation.getMovie().getMovieTitle())){
                Elements showTimeTablesForHallTypes = showTimeTablesForMovie.getElementsByClass("type-hall");
                for(Element showTimeTablesForHallType : showTimeTablesForHallTypes){

                    // 상영관 정보 파싱
                    Elements infoHall = showTimeTablesForHallType.select(".info-hall li");
                    String hallType = infoHall.get(0).text();
                    String hallName = infoHall.get(1).text();

                    if(hallType.contains(reservation.getHallType().getValue())){
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
