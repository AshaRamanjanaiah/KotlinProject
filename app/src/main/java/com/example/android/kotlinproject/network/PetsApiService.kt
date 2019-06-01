package com.example.android.kotlinproject.network

import com.example.android.kotlinproject.model.PetProperties
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.http.GET

private const val BASE_URL = "http://agl-developer-test.azurewebsites.net/"

private val retrofit = Retrofit.Builder()
    .addConverterFactory(MoshiConverterFactory.create())
    .baseUrl(BASE_URL)
    .build()

interface PetsApiService{
    @GET("people.json")
    fun getPetsInfo(): Call<List<PetProperties>>
}

object PetsApi{
    val retrofitService: PetsApiService by lazy{
        retrofit.create(PetsApiService::class.java)
    }
}