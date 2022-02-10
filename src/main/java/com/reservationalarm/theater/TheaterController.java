package com.reservationalarm.theater;

import com.reservationalarm.theater.domain.Theater;
import lombok.RequiredArgsConstructor;
import org.codehaus.jettison.json.JSONException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.List;

/*
*  TODO: 프론트에서 정확한 theaterName만 보내도록 할것
* */

@RestController
@RequiredArgsConstructor
public class TheaterController {
    private final TheaterBO theaterBO;

    @GetMapping("/theater")
    public ResponseEntity<List<Theater>> findAllTheater(){
        List<Theater> theaters = theaterBO.findAllTheater();
        return ResponseEntity.ok().body(theaters);
    }

    @GetMapping("/theater/{theaterName}")
    public ResponseEntity<Theater> findTheaterByName(@PathVariable String theaterName){
        Theater theater = theaterBO.findTheaterByName(theaterName);
        return ResponseEntity.ok().body(theater);
    }

    @PostMapping("/theater")
    public ResponseEntity<Integer> insertTheaterByCrawling() throws IOException, JSONException {
        theaterBO.insertTheaterByCrawling();
        return ResponseEntity.ok().body(200);
    }
}
