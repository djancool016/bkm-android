package com.example.bkmapp.data.remote.dto.responses

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class BbnsResponse(
    @SerialName("code")
    val code: Int,
    @SerialName("data")
    val data: BbnsData? = null,
    @SerialName("message")
    val message: String,
    @SerialName("status")
    val status: Boolean
)

@Serializable
data class BbnsData(
    @SerialName("lastMonth")
    val lastMonth: BbnsBalances,
    @SerialName("thisMonth")
    val thisMonth: BbnsBalances
)

@Serializable
data class BbnsBalances(
    @SerialName("aktiva")
    val aktiva: List<BbnsBalance>? = emptyList(),
    @SerialName("pasiva")
    val pasiva: List<BbnsBalance>? = emptyList()
)

@Serializable
data class BbnsBalance(
    @SerialName("account")
    val account: String,
    @SerialName("coa")
    val coa: String,
    @SerialName("credit")
    val credit: Int,
    @SerialName("debit")
    val debit: Int,
    @SerialName("id_account")
    val idAccount: Int,
    @SerialName("id_coa")
    val idCoa: Int
)