package com.waterflowflow.developmenttemplatekotlin.logic.model

data class TestResponse(val status:String, val test: Test)

data class Test(val id: Int, val name: String)