package com.example.bkmapp.data.remote.services

object HttpRoutes {
    private const val LOCALHOST = "127.0.0.1"
    private const val WIFI = "192.168.1.3"
    private const val TEST = "10.0.2.2"
    private const val BASE_URL = "http://$WIFI:5100/api"
    const val LKM = "$BASE_URL/lkm"
    const val KSM = "$BASE_URL/ksm"
    const val LOAN = "$BASE_URL/loan"
    const val LOAN_PAYMENT = "$BASE_URL/loanPayment"
    const val TRANSACTION = "$BASE_URL/transaction"
    const val TRANSACTION_LOAN = "$BASE_URL/transactionLoan"
    const val ACCOUNT = "$BASE_URL/account"
    const val BBNS = "$BASE_URL/report/bbns"
}