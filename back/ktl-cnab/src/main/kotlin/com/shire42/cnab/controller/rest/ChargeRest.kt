package com.shire42.cnab.controller.rest

import java.time.LocalDateTime
import java.util.*

data class ChargeRest(
    val id: UUID?,
    val amount: Double,
    val tag: String,
    val description: String?,
    val date: LocalDateTime?
) {
}