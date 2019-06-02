package com.example.android.kotlinproject

import com.example.android.kotlinproject.model.PetOwnerInfo

interface MainView {
    fun showProgress()
    fun hideProgress()
    fun setData(arrUpdates: List<PetOwnerInfo>?)
    fun setDataError(strError: String)
}