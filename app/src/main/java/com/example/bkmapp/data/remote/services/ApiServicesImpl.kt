package com.example.bkmapp.data.remote.services

import com.example.bkmapp.data.remote.dto.requests.ReportRequest
import com.example.bkmapp.data.remote.dto.responses.BbnsResponse
import com.example.bkmapp.utility.Result
import com.example.bkmapp.utility.Log
import io.ktor.client.*
import io.ktor.client.call.*
import io.ktor.client.plugins.*
import io.ktor.client.request.*
import io.ktor.client.statement.*
import io.ktor.http.*
import io.ktor.utils.io.errors.*
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class ApiServicesImpl(private val client: HttpClient): ApiServices {
    override suspend fun getBbns(reportRequest: ReportRequest): Flow<Result<BbnsResponse>> {
        val urlBuilder = URLBuilder(HttpRoutes.BBNS).apply {
            reportRequest.idLkm?.let { parameters.append("id_lkm", it.toString())}
            reportRequest.startDate?.let { parameters.append("start_date", it)}
            reportRequest.endDate?.let { parameters.append("end_date", it)}
        }
        return sendRequest(urlBuilder.buildString())
    }

    private suspend inline fun <reified T> sendRequest(url: String) : Flow<Result<T>>{
        return try {
            client.getJson(url)
        } catch (e: IOException){
            flow{
                emit(Result.Failed(Throwable("Can't connect to server", e)))
            }
        }
    }
}

suspend inline fun <reified T> HttpClient.getJson(url: String): Flow<Result<T>> {
    val response = this.get {
        url(url)
        contentType(ContentType.Application.Json)
    }
    return responseHandler { response }
}

suspend inline fun <reified T> responseHandler(crossinline response: () -> HttpResponse): Flow<Result<T>> = flow {
    try {
        emit(Result.Loading)
        val responseBody = response().body<T>()
        when(response().status.value){
            in 300..308   -> {
                throw RedirectResponseException(response(), "RedirectResponseException")
            }
            in 400..451  -> {
                throw ClientRequestException(response(), "ClientRequestException")
            }
            in 500..511  -> {
                throw ServerResponseException(response(), "ClientRequestException")
            }
        }
        if (responseBody != null) emit(Result.Success(responseBody))

    } catch (e: RedirectResponseException) {
        // 3xx - responses
        Log.e("RedirectResponseException", e.response.status.description)
        emit(Result.Failed(Throwable("${response().status}", e)))

    } catch (e: ClientRequestException) {
        // 4xx - responses
        Log.e("ClientRequestException", e.response.status.description)
        emit(Result.Failed(Throwable("${response().status}", e)))

    } catch (e: ServerResponseException) {
        // 5xx - responses
        Log.e("ServerResponseException", e.response.status.description)
        emit(Result.Failed(Throwable("${response().status}", e)))

    } catch (e: Exception) {
        e.message?.let {
            Log.e("Unknown Error", it)
            emit(Result.Failed(Throwable(it, e)))
        }
    }
}