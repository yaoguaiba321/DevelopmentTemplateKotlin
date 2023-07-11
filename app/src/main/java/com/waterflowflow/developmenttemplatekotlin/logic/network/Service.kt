package com.waterflowflow.developmenttemplatekotlin.logic.network


import com.waterflowflow.developmenttemplatekotlin.logic.model.TestResponse
import retrofit2.Call
import retrofit2.http.GET

interface Service {

    @GET("test")
    fun test(): Call<TestResponse>
}