package com.example.mad01.service

import android.app.Service
import android.content.Intent
import android.os.IBinder
import com.example.mad01.service.dto.DogFact
import retrofit2.Call
import retrofit2.http.GET

interface DogService{

    @GET("all")
    fun giveMeAllDog(): Call<List<DogFact>>
}