package com.sevenpeakssoftware.krishna.repository

import com.sevenpeakssoftware.krishna.database.CarsDao
import com.sevenpeakssoftware.krishna.response.CarsModel
import javax.inject.Inject

class DatabaseRepository @Inject constructor(private val dao: CarsDao) {


    suspend fun saveCarList(entity: MutableList<CarsModel?>) =
        dao.saveCarsList(entity)

    fun getAllCars() = dao.getAllCarsList()

    fun deleteAllCars() = dao.deleteAllCars()


}