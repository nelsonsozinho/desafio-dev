package com.shire42.cnab.model

import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.OneToMany
import javax.persistence.Table

@Entity
@Table(name = "cnab_transaction_type")
data class TransactionType(

    @Column(name = "type", unique = true)
    var type: Int,

    @Column(name = "description")
    var description: String,

    @Column(name = "nature")
    var nature: String,

    @Column(name = "signal")
    var signal: Char,

    @OneToMany(mappedBy = "type")
    var transactions: List<Transaction>? = null

):
    AbstractEntity() {
}
