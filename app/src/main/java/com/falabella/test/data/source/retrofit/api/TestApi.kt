package com.falabella.test.data.source.retrofit.api

import com.falabella.test.data.source.retrofit.response.ApiResponse
import retrofit2.Response
import retrofit2.http.GET

interface TestApi {
    @GET("api")
    suspend fun getEconomicIndicators(): Response<ApiResponse>
}