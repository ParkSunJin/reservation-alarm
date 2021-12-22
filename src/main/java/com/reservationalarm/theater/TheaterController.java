package com.reservationalarm.theater;

import com.reservationalarm.theater.domain.Theater;
import org.codehaus.jettison.json.JSONException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.List;

@RestController
public class TheaterController {
    @Autowired
    TheaterBO theaterBO;

    @GetMapping("/theater")
    public ResponseEntity<List<Theater>> findAllTheater(){
        List<Theater> theaters = theaterBO.findAllTheater();
        return ResponseEntity.ok().body(theaters);
    }

    @GetMapping("/theater/{theaterName}")
    public ResponseEntity<List<Theater>> findTheaterByName(@PathVariable String theaterName){
        List<Theater> theaters = theaterBO.findTheaterByName(theaterName);
        return ResponseEntity.ok().body(theaters);
    }

    @PostMapping("/theater")
    public ResponseEntity<Integer> insertTheaterByCrawling() throws IOException, JSONException {
        theaterBO.insertTheaterByCrawling();
        return ResponseEntity.ok().body(200);
    }
}
