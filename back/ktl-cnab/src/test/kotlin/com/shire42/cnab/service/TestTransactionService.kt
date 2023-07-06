package com.shire42.cnab.service

import com.shire42.cnab.TestWebEnvironmentConfigTest
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertNotNull
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.annotation.DirtiesContext
import org.testcontainers.junit.jupiter.Testcontainers

@SpringBootTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@Testcontainers
@DirtiesContext
class TestTransactionService: TestWebEnvironmentConfigTest() {

    @Autowired
    lateinit var service: TransactionService

    @Test
    fun `list transactions and check size`() {
        val transactions =  service.findTransactionsByStoreOwner("MERCADO DA AVENIDA")
        assertEquals(transactions.transactionAmount, 7100.0)
    }

    @Test
    fun `list transactions and check some value`() {
        val transaction = service.findTransactionsByStoreOwner("MERCADO DA AVENIDA")
        val transactionRest = transaction.transactions.filter { it.value ==  "0000080200".toDouble()}.last();
        assertNotNull(transactionRest)
    }

}