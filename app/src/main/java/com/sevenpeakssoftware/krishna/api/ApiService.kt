package com.sevenpeakssoftware.krishna.api

import com.sevenpeakssoftware.krishna.response.CarsDetailsResponse
import retrofit2.Response
import retrofit2.http.GET

interface ApiService {

    @GET("carlist")
    suspend fun getCarDetails(): Response<CarsDetailsResponse>

}
