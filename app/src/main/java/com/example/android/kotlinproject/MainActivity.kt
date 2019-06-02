package com.example.android.kotlinproject

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.View
import android.widget.ProgressBar
import com.example.android.kotlinproject.model.PetOwnerInfo
import com.example.android.kotlinproject.model.PetsInfo
import java.util.logging.Logger

class MainActivity : AppCompatActivity(), MainView {

    private val MALE_OWNER = "Male"
    private val FEMALE_OWNER = "Female"

    private lateinit var maleOwnerCatsRecyclerview: RecyclerView
    private lateinit var femaleOwnerCatsRecyclerview: RecyclerView
    private lateinit var progressBar: ProgressBar

    private lateinit var mainPresenter: MainPresenter

    private var myDataset: List<PetOwnerInfo>? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        progressBar = findViewById(R.id.progress_bar)

        var linearLayoutManagerMale = LinearLayoutManager(this)
        linearLayoutManagerMale.orientation = LinearLayoutManager.VERTICAL

        maleOwnerCatsRecyclerview = findViewById<RecyclerView>(R.id.rv_male_owner_cats).apply {

            setHasFixedSize(true)

            layoutManager = linearLayoutManagerMale

        }

        var linearLayoutManagerFemale = LinearLayoutManager(this)
        linearLayoutManagerFemale.orientation = LinearLayoutManager.VERTICAL

        femaleOwnerCatsRecyclerview = findViewById<RecyclerView>(R.id.rv_female_owner_cats).apply {

            setHasFixedSize(true)

            layoutManager = linearLayoutManagerFemale

        }

        mainPresenter = MainPresenter(this, MainInteractor())

        mainPresenter.getData()
    }

    override fun showProgress() {
        progressBar.visibility = View.VISIBLE
    }

    override fun hideProgress() {
        progressBar.visibility = View.GONE
    }

    override fun setData(arrUpdates: List<PetOwnerInfo>?) {
        if (arrUpdates != null) {
            myDataset = arrUpdates

            var arrMaleOwnersCats : List<PetsInfo> = getOwnersList(arrUpdates, MALE_OWNER)
            var arrFemaleOwnersCats : List<PetsInfo> = getOwnersList(arrUpdates, FEMALE_OWNER)

            var sortedListMaleOwnerCats = arrMaleOwnersCats.sortedWith(compareBy({ it.name }))
            var sortedListFemaleOwnerCats = arrFemaleOwnersCats.sortedWith(compareBy({ it.name }))


            var viewAdapterMale = OwnerAndPetInfoAdapter(this, sortedListMaleOwnerCats)
            maleOwnerCatsRecyclerview.adapter = viewAdapterMale

            var viewAdapterFemale = OwnerAndPetInfoAdapter(this, sortedListFemaleOwnerCats)
            femaleOwnerCatsRecyclerview.adapter = viewAdapterFemale
        }
    }

    fun getOwnersList(arrAllOwners: List<PetOwnerInfo>, gender: String): List<PetsInfo>{
        var arrOwners = ArrayList<PetOwnerInfo>()
        for (i in arrAllOwners.indices) {
            if(arrAllOwners[i].gender.equals(gender)){
                arrOwners.add(arrAllOwners[i])
            }
        }
        var arrOwnersCats: ArrayList<PetsInfo>

        arrOwnersCats = getCatsList(arrOwners)

        return arrOwnersCats
    }

    fun getCatsList(arrOwners: ArrayList<PetOwnerInfo>): ArrayList<PetsInfo>{
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

    override fun setDataError(strError: String) {
        Logger.getLogger(MainActivity::class.java.name).warning(getString(R.string.some_thing_went_wrong))
    }

}
