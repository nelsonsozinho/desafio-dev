package com.shire42.cnab.repository

import com.shire42.cnab.model.TransactionType
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository
import java.util.*

@Repository
interface TransactionTypeRepository: JpaRepository<TransactionType, UUID> {

    fun findByType(type: Int): Optional<TransactionType>

}