package com.example.android.kotlinproject

import com.example.android.kotlinproject.model.PetOwnerInfo
import com.example.android.kotlinproject.model.PetsInfo
import com.example.android.kotlinproject.network.PetsApi
import retrofit2.Call
import retrofit2.Response

class MainInteractor {

    interface OnFinishedListener{
        fun onResultSuccess(arrUpdates: List<PetOwnerInfo>?)
        fun onResultFailed(error: String)
    }

    fun requestGetDataAPI(onFinishedListener: OnFinishedListener){
        PetsApi.retrofitService.getPetsInfo().enqueue(object : retrofit2.Callback<List<PetOwnerInfo>> {
            override fun onFailure(call: Call<List<PetOwnerInfo>>, t: Throwable) {
                onFinishedListener.onResultFailed(t.toString())
            }

            override fun onResponse(call: Call<List<PetOwnerInfo>>, response: Response<List<PetOwnerInfo>>) {
               onFinishedListener.onResultSuccess(response.body())

            }

        })
    }

    fun getOwnersList(arrAllOwners: List<PetOwnerInfo>, gender: String): List<PetsInfo>?{
        var arrOwners = ArrayList<PetOwnerInfo>()
        for (i in arrAllOwners.indices) {
            if(arrAllOwners[i].gender.equals(gender)){
                arrOwners.add(arrAllOwners[i])
            }
        }
        var arrOwnersCats: ArrayList<PetsInfo>?

        arrOwnersCats = getCatsList(arrOwners)

        return arrOwnersCats
    }

    fun getCatsList(arrOwners: ArrayList<PetOwnerInfo>): ArrayList<PetsInfo>?{
        var arrOwnersCats = ArrayList<PetsInfo>()
        for(i in arrOwners){
            if(i.pets != null) {
                for (pet in i.pets) {
                    if (pet.type.equals("Cat")) {
                        arrOwnersCats.add(pet)
                    }
                }
            }
        }
        return arrOwnersCats
    }
}