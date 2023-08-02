package com.example.bkmapp.data.remote.dto.requests

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class ReportRequest(
    @SerialName("end_date")
    val endDate: String?,
    @SerialName("start_date")
    val startDate: String?,
    @SerialName("id_lkm")
    val idLkm: Int? = 1
)