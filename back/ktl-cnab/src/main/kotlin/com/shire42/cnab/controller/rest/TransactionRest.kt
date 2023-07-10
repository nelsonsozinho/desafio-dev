package com.shire42.cnab.controller.rest

import java.time.LocalDateTime

data class TransactionRest (
    var id: String,
    var data: LocalDateTime,
    var value: Double,
    var cpf: String,
    var cardNumber: String,
    var ownerName: String,
    var storeName: String,
    var nature: String,
    var transactionDescription: String
) {}