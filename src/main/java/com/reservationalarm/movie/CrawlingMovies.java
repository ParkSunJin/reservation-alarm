package com.reservationalarm.movie;

import com.reservationalarm.movie.domain.Movie;
import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.junit.Assert;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class CrawlingMovies {
    public static void main(String[] args) throws IOException, ParseException {
        String crawlingURL = "http://www.cgv.co.kr/movies/?lt=1&ft=0";

        Connection conn = Jsoup.connect(crawlingURL);
        Document document = conn.get();

        List<Movie> movies = new ArrayList<>();

        Elements moviesInfo = document.select("div.sect-movie-chart li");

        for(Element movieInfo:moviesInfo){
            // 영화 포스터 이미지 url
            String movieImageSrc = movieInfo.select(".thumb-image img")
                    .attr("abs:src");
            if(movieImageSrc == "") continue;
            // 영화 제목
            String movieTitle = movieInfo.select(".title").text();
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

            movies.add(Movie.builder()
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
