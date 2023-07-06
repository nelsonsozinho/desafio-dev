package com.shire42.cnab.repository

import com.shire42.cnab.model.Transaction
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository
import java.util.*

@Repository
interface TransactionRepository: JpaRepository<Transaction, UUID> {

    fun findByStoreName(storeName: String): List<Transaction>

}