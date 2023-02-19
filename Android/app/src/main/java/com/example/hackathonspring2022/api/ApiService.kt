package com.example.hackathonspring2022.api

import com.example.hackathonspring2022.api.models.Zhopa
import retrofit2.Response
import retrofit2.http.GET

interface ApiService {

    @GET("./getdashboard")
    suspend fun getZhopa(): Response<Zhopa>

}