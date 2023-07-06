package com.shire42.cnab.record.process

import com.shire42.cnab.record.Record
import java.io.BufferedReader
import java.io.InputStream

class CnabRecordFile(private val file: InputStream) {

    fun createRecords(): List<Record> {
        val records = ArrayList<Record>()
        val lines = readLines();
        lines.forEach {
            records.add(TransactionRecord(it))
        }
        return records
    }

    private fun readLines(): List<String> {
        val reader = BufferedReader(file.reader())
        reader.use { stream ->
            return stream.readLines()
        }
    }

}