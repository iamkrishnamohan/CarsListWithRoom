package com.sevenpeakssoftware.krishna.repository

import com.sevenpeakssoftware.krishna.api.ApiService
import com.sevenpeakssoftware.krishna.response.CarsDetailsResponse
import com.sevenpeakssoftware.krishna.utils.NetworkResponse
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject


class ApiRepository @Inject constructor(private val apiServices: ApiService) {

    fun getPopularCarsList(): Flow<NetworkResponse<CarsDetailsResponse>> =
        flow {
            emit(NetworkResponse.Loading)
            val response = apiServices.getCarDetails()
            when {
                response.isSuccessful -> {
                    emit(NetworkResponse.Success(response.body()))
                }

                else -> {
                    emit(NetworkResponse.Error(response.message()))
                }
            }
        }


}
