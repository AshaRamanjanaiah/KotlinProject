package com.example.android.kotlinproject

import com.example.android.kotlinproject.model.PetProperties

interface MainView {
    fun showProgress()
    fun hideProgress()
    fun setData(arrUpdates: List<PetProperties>?)
    fun setDataError(strError: String)
}