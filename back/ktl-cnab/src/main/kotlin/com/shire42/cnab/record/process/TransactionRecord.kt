package com.shire42.cnab.record.process

import com.shire42.cnab.record.Record
import org.apache.commons.lang3.StringUtils
import java.time.LocalDateTime

class TransactionRecord(private val record: String): Record {

    override fun findType(): Int {
        return Integer.parseInt(StringUtils.substring(record, 0, 1))
    }

    override fun findData(): LocalDateTime {
        val dateStr = StringUtils.substring(record, 1, 9);
        val time = findHour();
        return LocalDateTime.of(
            Integer.parseInt(StringUtils.substring(dateStr, 0, 4)),
            Integer.parseInt(StringUtils.substring(dateStr, 4, 6)),
            Integer.parseInt(StringUtils.substring(dateStr, 6, 8)),

            Integer.parseInt(StringUtils.substring(time, 0, 2)),
            Integer.parseInt(StringUtils.substring(time, 2, 4)),
            Integer.parseInt(StringUtils.substring(time, 4, 6))
        )
    }

    override fun findValue(): Double {
        var value = StringUtils.substring(record, 10, 19)
        return value.toDouble() / 100
    }

    override fun findCPF(): String {
        return StringUtils.substring(record, 19, 30)
    }

    override fun findCardNumber(): String {
        return StringUtils.substring(record, 30, 42);
    }

    override fun findHour(): String {
        return StringUtils.substring(record, 42, 48)
    }

    override fun findStoreOwner(): String {
        return StringUtils.substring(record, 48, 62)
    }

    override fun findStoreName(): String {
        return StringUtils.substring(record, 62, 81)
    }

}