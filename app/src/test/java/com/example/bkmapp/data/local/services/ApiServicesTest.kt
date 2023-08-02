package com.example.bkmapp.data.local.services

import com.example.bkmapp.data.local.dto.requests.ApiRequestDummy
import com.example.bkmapp.data.local.dto.responses.ApiResponseDummy
import com.example.bkmapp.data.remote.services.ApiServices
import com.example.bkmapp.utility.Result
import com.example.bkmapp.utility.Log
import kotlinx.coroutines.runBlocking
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import kotlin.test.assertEquals

@RunWith(JUnit4::class)
class ApiServicesTest {
    private val apiServices = ApiServices.getInstance()

    @Test
    fun getBbnsTest() = runBlocking {
        val reportRequest = ApiRequestDummy.reportRequest()
        apiServices.getBbns(reportRequest).collect { result ->
            when(result){
                is Result.Loading -> Log.i("Status", "Loading...")
                is Result.Failed -> throw result.error
                is Result.Success -> assertEquals(result.value, ApiResponseDummy.bbnsResponse())
            }
        }
    }
}