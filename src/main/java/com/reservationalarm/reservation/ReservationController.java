package com.reservationalarm.reservation;

import com.reservationalarm.reservation.domain.Reservation;
import com.reservationalarm.reservation.model.MovieTime;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;

@RestController
public class ReservationController {
    @PostMapping("/reservation")
    public ResponseEntity<ArrayList<MovieTime>> makeReservation(@RequestBody Reservation reservation) throws IOException {
        ReservationBO reservationBO = new ReservationBO();
        ArrayList<MovieTime> movieTimes = reservationBO.findReservation(reservation);
        return ResponseEntity.ok().body(movieTimes);
    }
}
