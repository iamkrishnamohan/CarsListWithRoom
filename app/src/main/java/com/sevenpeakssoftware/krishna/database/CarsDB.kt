package com.sevenpeakssoftware.krishna.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.sevenpeakssoftware.krishna.response.CarsModel

@Database(entities = [CarsModel::class], version = 1, exportSchema = false)
abstract class CarsDB : RoomDatabase() {

    abstract fun carsDao(): CarsDao

}