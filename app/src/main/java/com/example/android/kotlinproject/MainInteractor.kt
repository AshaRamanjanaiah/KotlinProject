package com.example.android.kotlinproject

import com.example.android.kotlinproject.model.PetProperties
import com.example.android.kotlinproject.network.PetsApi
import retrofit2.Call
import retrofit2.Response

class MainInteractor {
    interface OnFinishedListener{
        fun onResultSuccess(arrUpdates: List<PetProperties>?)
        fun onResultFailed(error: String)
    }

    fun requestGetDataAPI(onFinishedListener: OnFinishedListener){
        PetsApi.retrofitService.getPetsInfo().enqueue(object : retrofit2.Callback<List<PetProperties>> {
            override fun onFailure(call: Call<List<PetProperties>>, t: Throwable) {
                onFinishedListener.onResultFailed(t.toString())
            }

            override fun onResponse(call: Call<List<PetProperties>>, response: Response<List<PetProperties>>) {
               onFinishedListener.onResultSuccess(response.body())

            }

        })
    }
}