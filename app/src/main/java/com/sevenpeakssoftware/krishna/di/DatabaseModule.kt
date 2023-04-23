package com.sevenpeakssoftware.krishna.di

import android.content.Context
import androidx.room.Room
import com.sevenpeakssoftware.krishna.database.CarsDB
import com.sevenpeakssoftware.krishna.response.CarsModel
import com.sevenpeakssoftware.krishna.utils.Constants.CARS_DATABASE
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {

    @Provides
    @Singleton
    fun provideDatabase(@ApplicationContext context: Context) = Room.databaseBuilder(
        context, CarsDB::class.java, CARS_DATABASE
    ).allowMainThreadQueries()
        .fallbackToDestructiveMigration().build()


    @Provides
    @Singleton
    fun provideDao(db: CarsDB) = db.carsDao()


    @Provides
    fun provideEntity() = CarsModel()
}