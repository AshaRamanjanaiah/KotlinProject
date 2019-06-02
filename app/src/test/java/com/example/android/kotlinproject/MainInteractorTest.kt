package com.example.android.kotlinproject

import com.example.android.kotlinproject.model.PetOwnerInfo
import com.example.android.kotlinproject.model.PetsInfo
import junit.framework.Assert.assertEquals
import org.junit.Test

class MainInteractorTest {

    private val mainInteractor = MainInteractor()

    @Test
    fun getCatsListTest_petsFieldNotNull() {
        var arrOwnersCats = ArrayList<PetsInfo>()

        arrOwnersCats.add(PetsInfo("Puppy", "Cat"))
        arrOwnersCats.add(PetsInfo("Jim", "Cat"))
        arrOwnersCats.add(PetsInfo("Max", "Cat"))
        arrOwnersCats.add(PetsInfo("Tim", "Dog"))

        var arrOwnersCatsNew = ArrayList<PetsInfo>()
        arrOwnersCatsNew.add(PetsInfo("Puppy", "Cat"))
        arrOwnersCatsNew.add(PetsInfo("Jim", "Cat"))
        arrOwnersCatsNew.add(PetsInfo("Max", "Cat"))

        var arrPetOwnerinfo = ArrayList<PetOwnerInfo>()
        arrPetOwnerinfo.add(PetOwnerInfo("Male", arrOwnersCats))

        assertEquals(mainInteractor.getCatsList(arrPetOwnerinfo), arrOwnersCatsNew)
    }

    @Test
    fun getCatsListTest_petsFieldNull(){
        var arrPetOwnerinfo = ArrayList<PetOwnerInfo>()
        arrPetOwnerinfo.add(PetOwnerInfo("Male", null))

        var arrOwnersCats = ArrayList<PetsInfo>()

        assertEquals(mainInteractor.getCatsList(arrPetOwnerinfo), arrOwnersCats)
    }
}