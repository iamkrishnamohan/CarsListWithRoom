package com.sevenpeakssoftware.krishna.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sevenpeakssoftware.krishna.repository.ApiRepository
import com.sevenpeakssoftware.krishna.response.CarsDetailsResponse
import com.sevenpeakssoftware.krishna.utils.NetworkResponse
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CarsViewModel @Inject constructor(private val repository: ApiRepository) : ViewModel() {

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

}
