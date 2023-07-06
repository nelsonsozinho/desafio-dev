package com.shire42.cnab.controller.rest

data class TransactionSummarizeRest(
    val transactions: List<TransactionRest>,
    val transactionAmount: Double
)