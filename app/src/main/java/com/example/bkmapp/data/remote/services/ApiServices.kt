package com.example.bkmapp.data.remote.services

import com.example.bkmapp.data.remote.dto.requests.ReportRequest
import com.example.bkmapp.data.remote.dto.responses.BbnsResponse
import com.example.bkmapp.utility.Result
import io.ktor.client.*
import io.ktor.client.engine.android.*
import io.ktor.client.plugins.contentnegotiation.*
import io.ktor.client.plugins.logging.*
import io.ktor.serialization.kotlinx.json.*
import kotlinx.coroutines.flow.Flow

interface ApiServices {
    suspend fun getBbns(reportRequest: ReportRequest): Flow<Result<BbnsResponse>>

    companion object {
        fun getInstance(): ApiServices {
            return ApiServicesImpl(
                client = HttpClient(Android){
                    install(Logging){
                        level = LogLevel.ALL
                    }
                    install(ContentNegotiation){
                        json()
                    }
                }
            )
        }
    }
}