package com.shire42.cnab.record.process

import com.shire42.cnab.record.Record
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.BeforeAll
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import java.time.LocalDate
import java.time.LocalDateTime

class TestProcess {

    var record: Record? = null

    @BeforeEach
    fun setup() {
        val recordStr = "2201903010000010700845152540738723****9987123333MARCOS PEREIRAMERCADO DA AVENIDA"
        record = TransactionRecord(recordStr);
    }

    @Test
    fun `test real value from record`() {
        val realValue = "107".toDouble();
        assertEquals(realValue, record?.findValue());
    }

    @Test
    fun `test cpf from record`() {
        val realValue ="84515254073";
        assertEquals(realValue, record?.findCPF());
    }

    @Test
    fun `test card number from record`() {
        val realValue ="8723****9987";
        assertEquals(realValue, record?.findCardNumber())
    }

    @Test
    fun `test hour from record`() {
        val realValue ="123333";
        assertEquals(realValue, record?.findHour())
    }

    @Test
    fun `test store owner from record`() {
        val realValue ="MARCOS PEREIRA";
        assertEquals(realValue, record?.findStoreOwner())
    }

    @Test
    fun `test store name from record`() {
        val realValue ="MERCADO DA AVENIDA";
        assertEquals(realValue, record?.findStoreName())
    }

    @Test
    fun `test type from record`() {
        val realValue = 2;
        assertEquals(realValue, record?.findType())
    }


    @Test
    fun `test data from record`() {
        val realValue = LocalDateTime.of(2019, 3, 1, 12, 33, 33);
        assertEquals(realValue, record?.findData())
    }


}