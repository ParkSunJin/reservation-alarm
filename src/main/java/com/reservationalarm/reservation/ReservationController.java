package com.reservationalarm.reservation;

import com.reservationalarm.reservation.domain.Reservation;
import com.reservationalarm.reservation.model.ReservationCreateDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.List;

@RestController
@RequiredArgsConstructor
public class ReservationController {
    private final ReservationBO reservationBO;

    @PostMapping("/reservation")
    public ResponseEntity<Integer> makeReservation(@RequestBody ReservationCreateDTO reservationCreateDTO) throws IOException {
        reservationBO.makeReservation(reservationCreateDTO);
        return ResponseEntity.ok().body(200);
    }


    @GetMapping("/reservation")
    public ResponseEntity<List<Reservation>> findAll(){
        List<Reservation> reservations = reservationBO.findAll();
        return ResponseEntity.ok().body(reservations);
    }
}
