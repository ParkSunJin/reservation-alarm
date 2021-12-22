package com.reservationalarm.theater;

import com.reservationalarm.theater.domain.Theater;
import org.codehaus.jettison.json.JSONArray;
import org.codehaus.jettison.json.JSONException;
import org.codehaus.jettison.json.JSONObject;
import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Service
public class TheaterBO {
    @Autowired
    TheaterDAO theaterDAO;

    // 영화관 정보 크롤링 url
    private final static String crawlingURL = "http://www.cgv.co.kr/theaters/?areacode=01&theaterCode=0056";

    public List<Theater> findAllTheater(){
        return theaterDAO.selectAllTheater();
    }

    public List<Theater> findTheaterByName(String theaterName){
        if(theaterName.isEmpty()) return null;
        return theaterDAO.selectTheaterByName(theaterName);
    }

    public void insertTheaterByCrawling() throws IOException, JSONException {
        theaterDAO.deleteAllTheater();
        Document document = crawlingFromUrl();

        String theaterJsonData = parseTheaterJsonData(document);

        List<Theater> theaters= convert(theaterJsonData);

        theaters.stream()
                .forEach(theaterDAO::insertTheater);
    }

    private Document crawlingFromUrl() throws IOException {
        Connection conn = Jsoup.connect(crawlingURL);
        Document document = conn.get();
        return document;
    }

    // 크롤링한 document로부터 theaterJsonData만 String 타입으로 파싱
    private String parseTheaterJsonData(Document document){
        Elements scripts = document.getElementsByTag("script");

        String theaterJsonData = scripts.stream()
                .filter(script -> script.data().contains("var theaterJsonData"))
                .map(script -> {
                    Pattern pattern = Pattern.compile("(var theaterJsonData = )([^;]*)");
                    Matcher matcher = pattern.matcher(script.data());
                    return matcher;
                })
                .filter(matcher -> matcher.find())
                .findAny()
                .map(matcher -> matcher.group(2))
                .orElse(null);
        return theaterJsonData;
    }

    private List<Theater> convert(String theaterJsonData) throws JSONException {

        List<Theater> theaters = new ArrayList<>();

        JSONArray theaterInfoJsonArray = new JSONArray(theaterJsonData);
        for(int i=0;i<theaterInfoJsonArray.length();i++){
            JSONObject curRegion = theaterInfoJsonArray.getJSONObject(i);

            String regionName = curRegion.getString("RegionName");
            String regionNameENG = curRegion.getString("RegionName_ENG");

            JSONArray AreaTheaterDetailList = curRegion.getJSONArray("AreaTheaterDetailList");
            for(int j=0;j<AreaTheaterDetailList.length();j++){
                JSONObject curTheaterInfo = AreaTheaterDetailList.getJSONObject(j);

                Theater theater = Theater.builder()
                        .theaterCode(curTheaterInfo.getString("TheaterCode"))
                        .theaterName(curTheaterInfo.getString("TheaterName"))
                        .theaterNameEng(curTheaterInfo.getString("TheaterName_ENG"))
                        .areaCode(curTheaterInfo.getString("RegionCode"))
                        .areaName(regionName)
                        .areaNameEng(regionNameENG)
                        .build();

                theaters.add(theater);
            }
        }
        return theaters;
    }
}
