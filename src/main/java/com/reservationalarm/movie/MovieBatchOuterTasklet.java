package com.reservationalarm.movie;

import com.reservationalarm.movie.domain.Movie;
import lombok.RequiredArgsConstructor;
import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.batch.core.StepContribution;
import org.springframework.batch.core.scope.context.ChunkContext;
import org.springframework.batch.core.step.tasklet.Tasklet;
import org.springframework.batch.repeat.RepeatStatus;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Objects;

@Component
@RequiredArgsConstructor
public class MovieBatchOuterTasklet implements Tasklet {
    private final MovieDAO movieDAO;

    // 영화 정보 크롤링 url
    private final static String crawlingURL = "http://www.cgv.co.kr/movies/?lt=1&ft=0";

    @Override
    public RepeatStatus execute(StepContribution contribution, ChunkContext chunkContext) throws Exception {

        // 영화 정보 크롤링해서 DB에 저장하기 전에 그 전의 데이터는 모두 삭제한다.
        movieDAO.deleteAllMovie();
        // auto increment 초기화
        movieDAO.initializeAutoIncrement();

        Document document = crawlingFromUrl();

        Elements moviesInfo = document.select("div.sect-movie-chart li");

        moviesInfo.stream()
                .map(this::convert)
                .filter(Objects::nonNull)
                .forEach(movieDAO::insertMovie);

        return RepeatStatus.FINISHED;
    }

    private Document crawlingFromUrl() throws IOException {
        Connection conn = Jsoup.connect(crawlingURL);
        Document document = conn.get();
        return document;
    }

    private Movie convert(Element movieInfo) {
        // 영화 포스터 이미지 url
        String movieImageSrc = movieInfo.select(".thumb-image img")
                .attr("abs:src");
        // 영화 제목
        String movieTitle = movieInfo.select(".title").text();
        if (movieImageSrc == "") return null;
        // 예매율
        String movieScoreStr = movieInfo.select(".score strong")
                .text()
                .replace("예매율", "")
                .replace("%", "");
        Double movieScore = Double.parseDouble(movieScoreStr);
        // 개봉 날짜
        String openingDateStr = movieInfo.select(".txt-info").text()
                .substring(0, 10);
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy.MM.dd");
        Date openingDate = null;
        try {
            openingDate = dateFormat.parse(openingDateStr);
        } catch (ParseException e) {
            e.printStackTrace();
            return null;
        }

        // 개봉 여부
        Elements dday = movieInfo.select(".dday");
        Boolean isOpened = (dday.isEmpty()) ? true : false;

        // 예매 링크
        String reservationLink = movieInfo.select(".like a")
                .attr("abs:href");

        return Movie.builder()
                .movieTitle(movieTitle)
                .movieImageSrc(movieImageSrc)
                .movieScore(movieScore)
                .openingDate(openingDate)
                .isOpened(isOpened)
                .reservationLink(reservationLink)
                .build();
    }
}
