package com.shire42.cnab.model

import java.time.LocalDateTime
import javax.persistence.CascadeType
import javax.persistence.Column
import javax.persistence.ManyToOne
import javax.persistence.Table

@Table(name = "cnab_transaction")
data class Transaction(

    @ManyToOne(cascade = [CascadeType.ALL])
    var type: TransactionType,

    @Column(name = "data")
    var data: LocalDateTime,

    @Column(name = "value")
    var value: Double,

    @Column(name = "cpf")
    var cpf: String,

    @Column(name = "card_number")
    var cardNumber: String,

    @Column(name = "hour")
    var hour: String,

    @Column(name = "owner_name")
    var ownerName: String,


    @Column(name = "store_name")
    var storeName: String,

):
    AbstractEntity() {

}