package com.sean.p3

import retrofit2.Callback
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class CovidDataService{
    private val service: CovidDataApi

    init{
        val retrofit = Retrofit.Builder().
        baseUrl(BuildConfig.COVID_WEB_SERVER).
        addConverterFactory(GsonConverterFactory.create()).
        build()
        service = retrofit.create(CovidDataApi::class.java)
    }

    fun getCovidData(callback: Callback<List<CovidDataResponse>>, state: String){
        val call = service.getCovidData(state)
        call.enqueue(callback)
    }
}