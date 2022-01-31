package com.example.cloudcall

import retrofit2.Call
import retrofit2.http.Query
import retrofit2.http.GET

interface Data_Collector {

    @GET("weather")
    fun getPost(@Query("q") city_name : String,
                @Query("appid") api_id : String,
                @Query("units") units : String
    ) : Call<WeatherInfo>

}