package com.example.mad01

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.mad01.service.DogService
import com.example.mad01.service.dto.DogFact
import retrofit2.*
import retrofit2.converter.gson.GsonConverterFactory

class MainActivity : AppCompatActivity() {
    private lateinit var rvDogFactList: RecyclerView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        rvDogFactList = findViewById(R.id.rv_dogfactlist)
        rvDogFactList.layoutManager = LinearLayoutManager(this)


        val dogService = buildService()
        dogService.giveMeAllDog().enqueue(object : Callback<List<DogFact>> {
            override fun onResponse(call: Call<List<DogFact>>, response: Response<List<DogFact>>) {
                Log.i("asdf", "onResponse()")
            }
            override fun onFailure(call: Call<List<DogFact>>, t: Throwable){
                Log.i("asdf", "dog was unable to be summoned")
            }
        })
        rvDogFactList.adapter = MyItemAdapter(
            dogService.giveMeAllDog()
        )

    }

    private fun buildService(): DogService {
        val retrofit = Retrofit.Builder()
            .baseUrl("https://dog-facts-api.herokuapp.com/api/v1/resources/dogs/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        return retrofit.create(DogService::class.java)
    }
}