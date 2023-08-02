package com.example.bkmapp.data.local.dto.requests

import com.example.bkmapp.data.remote.dto.requests.ReportRequest

object ApiRequestDummy {
    fun reportRequest() : ReportRequest {
        return ReportRequest(
            idLkm = 1,
            startDate = "2023-01-01",
            endDate = "2023-01-31"
        )
    }
}