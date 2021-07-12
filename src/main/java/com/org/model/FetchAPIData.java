package com.org.model;


import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;

public class FetchAPIData {
    WeatherInfo weatherInfo;
    @Test
    public WeatherInfo callApiAndStoreData(){
        weatherInfo=new WeatherInfo();
        RestAssured.baseURI="https://api.openweathermap.org";
        RestAssured.basePath="/data/2.5/weather";
        String token="7fe67bf08c80ded756e598d6f8fedaea";
        String measure="metric";

        Response response1 =given().
                param("q","Pune").
                param("appid",token).
                param("units",measure)
                .when().
                        get()
                .then()
                .extract().response();
        try
        {
            JSONParser parser=new JSONParser();

            JSONObject json=(JSONObject) parser.parse(response1.body().asString());
            //String temp=(String) json.get("main.temp");
            JSONObject mainobj = (JSONObject) json.get("main");
            System.out.println("@@#$$!"+mainobj);
            double temp = (double) mainobj.get("temp");
            long humidity = (long) mainobj.get("humidity");
            weatherInfo.setTemp(temp);
            weatherInfo.setHumidity(humidity);
            System.out.println("@@#$$!"+temp);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return weatherInfo;
    }

}
