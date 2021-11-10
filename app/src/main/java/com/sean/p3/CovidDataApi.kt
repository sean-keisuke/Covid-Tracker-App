package com.sean.p3

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

interface CovidDataApi{
    @GET("states/{state}/daily.json")
    fun getCovidData(@Path("state") state:String): Call<List<CovidDataResponse>>
}
