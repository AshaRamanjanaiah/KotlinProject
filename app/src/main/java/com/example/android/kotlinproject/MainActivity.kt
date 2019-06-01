package com.example.android.kotlinproject

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import com.example.android.kotlinproject.model.PetProperties

class MainActivity : AppCompatActivity(), MainView {

    private lateinit var textViewTest: TextView
    private lateinit var mainPresenter: MainPresenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        textViewTest = findViewById(R.id.tv_testData)

        mainPresenter = MainPresenter(this, MainInteractor())

        mainPresenter.getData()
    }

    override fun showProgress() {

    }

    override fun hideProgress() {

    }

    override fun setData(arrUpdates: List<PetProperties>?) {
        if (arrUpdates != null) {
            this.textViewTest.text = arrUpdates.size.toString()
        }
    }

    override fun setDataError(strError: String) {
        this.textViewTest.text = getString(R.string.some_thing_went_wrong)
    }

}
