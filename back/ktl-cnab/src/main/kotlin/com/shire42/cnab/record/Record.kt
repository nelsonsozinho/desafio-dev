package com.shire42.cnab.record

import java.time.LocalDateTime

interface Record {

    fun findType(): Int

    fun findData(): LocalDateTime

    fun findValue(): Double

    fun findCPF(): String

    fun findCardNumber(): String

    fun findHour(): String

    fun findStoreOwner(): String

    fun findStoreName(): String

}