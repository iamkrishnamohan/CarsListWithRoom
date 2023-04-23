package com.sevenpeakssoftware.krishna.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.sevenpeakssoftware.krishna.response.CarsModel
import com.sevenpeakssoftware.krishna.utils.Constants.CARS_TABLE
import kotlinx.coroutines.flow.Flow

@Dao
interface CarsDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun saveCarsList(contactsEntity: MutableList<CarsModel?>)

    @Query("SELECT * FROM $CARS_TABLE")
    fun getAllCarsList(): Flow<MutableList<CarsModel?>>

    @Query("DELETE FROM $CARS_TABLE")
    fun deleteAllCars()


}