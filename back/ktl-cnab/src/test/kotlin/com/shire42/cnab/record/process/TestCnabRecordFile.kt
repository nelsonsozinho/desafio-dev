package com.shire42.cnab.record.process

import com.shire42.cnab.record.Record
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.testcontainers.shaded.com.google.common.io.Resources
import java.io.FileInputStream
import java.io.InputStream

class TestCnabRecordFile {

    var file: InputStream? = null

    var cnabRecors: CnabRecordFile? = null

    @BeforeEach
    fun setup() {
        val url = Resources.getResource("cnab/CNAB.txt")
        this.file = FileInputStream(url.file)
        this.cnabRecors = CnabRecordFile(this.file as FileInputStream)
    }

    @Test
    fun `chacke size of record list`() {
        val records: List<Record>? = this.cnabRecors?.createRecords()
        assertTrue(records?.size == 21)
    }

    @Test
    fun `get one to check`() {
        val records: List<Record>? = this.cnabRecors?.createRecords()
        val record = records?.get(20)
        assertEquals(record?.findCPF(), "84515254073")
        assertEquals(record?.findStoreName(), "MERCADO DA AVENIDA")
        assertEquals(record?.findValue(), "0000019200".toDouble() / 100)
    }

}