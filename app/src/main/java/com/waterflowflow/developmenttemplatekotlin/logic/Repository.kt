package com.waterflowflow.developmenttemplatekotlin.logic

import androidx.lifecycle.liveData
import com.waterflowflow.developmenttemplatekotlin.logic.network.Netwrok
import kotlinx.coroutines.Dispatchers
import java.lang.Exception
import java.lang.RuntimeException

object Repository {

    fun test() = liveData(Dispatchers.IO){
        val result = try{
            val testResponse = Netwrok.test()
            if(testResponse.status.equals("ok") ){
                //do something
                Result.success(testResponse)
            }else{
                //do something
                Result.failure(RuntimeException("response status is ${testResponse.status}"))
            }
        }catch (e : Exception){
            Result.failure<String>(e)
        }
        emit(result)
    }
}