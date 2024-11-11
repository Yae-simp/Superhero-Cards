package com.example.superherocards.utils

import com.example.superherocards.services.SuperheroService
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class RetrofitProvider {
    companion object {
        fun getRetrofit() : SuperheroService {
            val retrofit = Retrofit.Builder()
                .baseUrl("https://superheroapi.com/api/17f1ccff525ef3de4f98aeb8c2339b9d/")
                .addConverterFactory(GsonConverterFactory.create())
                .build()
            return retrofit.create(SuperheroService::class.java)
        }
    }
}