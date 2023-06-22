package com.example.weatherapplication;


import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface WeatherAPI {

    @GET("weather?appid=b56473d0574ba938fddbab782c41833a&units=metric")
    Call<OpenWeathwerMap> getWeatherWithLocation(@Query("lat")double lat, @Query("lon")double lon);

    @GET("weather?appid=b56473d0574ba938fddbab782c41833a&units=metric")
    Call<OpenWeathwerMap>getWeatherWithCityName(@Query("q")String name);
}
