package com.sevenpeakssoftware.krishna.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sevenpeakssoftware.krishna.repository.ApiRepository
import com.sevenpeakssoftware.krishna.repository.DatabaseRepository
import com.sevenpeakssoftware.krishna.response.CarsDetailsResponse
import com.sevenpeakssoftware.krishna.response.CarsModel
import com.sevenpeakssoftware.krishna.utils.NetworkResponse
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CarsViewModel @Inject constructor(
    private val repository: ApiRepository,
    private val databaseRepository: DatabaseRepository
) : ViewModel() {

    private var mutableUserOutletTypesInfoData =
        MutableLiveData<NetworkResponse<CarsDetailsResponse>>()

    val returnUserOutletTypesInfoData = mutableUserOutletTypesInfoData

    fun fetchCarDetailsData() {
        viewModelScope.launch {
            repository.getPopularCarsList().collect {
                mutableUserOutletTypesInfoData.value = it

            }
        }
    }

    private val _carsList = MutableLiveData<MutableList<CarsModel?>>()
    val carsList: LiveData<MutableList<CarsModel?>>
        get() = _carsList

    fun getAllCarsListFromLocalDB() = viewModelScope.launch {
        databaseRepository.getAllCars()
            .collect { _carsList.value = it }
    }

    fun saveAllCarsDetailsInLocalDB(carsList: MutableList<CarsModel?>) =
        viewModelScope.launch {
            databaseRepository.saveCarList(carsList)
        }

    fun deleteCarsListFromLocalDB() = viewModelScope.launch {
        databaseRepository.deleteAllCars()
    }

}
