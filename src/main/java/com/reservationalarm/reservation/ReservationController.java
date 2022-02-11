package com.reservationalarm.reservation;

import com.reservationalarm.reservation.model.ReservationCreateDTO;
import com.reservationalarm.reservation.model.ReservationReadDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.List;

/*
 *  TODO: 프론트에서 정확한 theaterName, movieTitle만 보내도록 할것
 * */

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
    public ResponseEntity<List<ReservationReadDTO>> findAll(){
        List<ReservationReadDTO> reservations = reservationBO.findAll();
        return ResponseEntity.ok().body(reservations);
    }
}
