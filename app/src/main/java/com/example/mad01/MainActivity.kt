package com.example.mad01

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.example.mad01.service.DogService
import com.example.mad01.service.dto.DogFact
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val dogService = buildService()
        dogService.giveMeDog().enqueue(object : Callback<DogFact> {
            override fun onResponse(call: Call<DogFact>, response: Response<DogFact>){
                Log.i("asdf", "onResponse()")
            }
            override fun onFailure(call: Call<DogFact>, t: Throwable){
                Log.i("asdf", "dog was unable to be summoned")
            }
        })
    }

    private fun buildService(): DogService {
        val retrofit = Retrofit.Builder()
            .baseUrl("https://dog-facts-api.herokuapp.com/api/v1/resources/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        return retrofit.create(DogService::class.java)
    }
}