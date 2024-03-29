package com.example.android.kotlinproject

import com.example.android.kotlinproject.model.PetOwnerInfo

class MainPresenter(private var mainView: MainView?, private val mainInteractor: MainInteractor)
    : MainInteractor.OnFinishedListener {

    fun getData(){
        mainView?.showProgress()
        mainInteractor.requestGetDataAPI(this)
    }

    override fun onResultSuccess(arrUpdates: List<PetOwnerInfo>?) {
        mainView?.hideProgress()
        mainView?.setData(arrUpdates)
    }

    override fun onResultFailed(error: String) {
        mainView?.hideProgress()
        mainView?.setDataError(error)
    }

    fun onDestroy(){
        mainView = null
    }
}