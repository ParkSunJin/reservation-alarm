package com.reservationalarm.movie;

import com.reservationalarm.movie.domain.Movie;
import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Service
public class MovieBO {
    @Autowired
    MovieMapper movieMapper;

    public Movie findMovieById(Integer movieId){
        return movieMapper.selectMovieById(movieId);
    }

    public List<Movie> findMovieByTitle(String movieTitle){
        if(movieTitle.isEmpty()) return null;
        return movieMapper.selectMovieByTitle(movieTitle);
    }

    public List<Movie> findAllMovie(){
        return movieMapper.selectAllMovie();
    }

    public void insertMovieByCrawling() throws IOException, ParseException {
        String crawlingURL = "http://www.cgv.co.kr/movies/?lt=1&ft=0";

        Connection conn = Jsoup.connect(crawlingURL);
        Document document = conn.get();

        movieMapper.deleteAllMovie();

        Elements moviesInfo = document.select("div.sect-movie-chart li");

        for(Element movieInfo:moviesInfo){
            // 영화 포스터 이미지 url
            String movieImageSrc = movieInfo.select(".thumb-image img")
                    .attr("abs:src");
            // 영화 제목
            String movieTitle = movieInfo.select(".title").text();
            if(movieImageSrc == "") continue;
            // 예매율
            String movieScoreStr = movieInfo.select(".score strong")
                    .text()
                    .replace("예매율","")
                    .replace("%","");
            Double movieScore = Double.parseDouble(movieScoreStr);
            // 개봉 날짜
            String openingDateStr = movieInfo.select(".txt-info").text()
                    .substring(0, 10);
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy.mm.dd");
            Date openingDate = dateFormat.parse(openingDateStr);

            // 개봉 여부
            Elements dday = movieInfo.select(".dday");
            Boolean isOpened = (dday.isEmpty()) ? true : false;

            // 예매 링크
            String reservationLink = movieInfo.select(".like a")
                    .attr("abs:href");

            movieMapper.insertMovie(Movie.builder()
                    .movieTitle(movieTitle)
                    .movieImageSrc(movieImageSrc)
                    .movieScore(movieScore)
                    .openingDate(openingDate)
                    .isOpened(isOpened)
                    .reservationLink(reservationLink)
                    .build());
        }
    }
}
