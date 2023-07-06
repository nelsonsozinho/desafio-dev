package com.shire42.cnab.service

import com.shire42.cnab.controller.rest.TransactionRest
import com.shire42.cnab.controller.rest.TransactionSummarizeRest
import com.shire42.cnab.model.Transaction
import com.shire42.cnab.model.TransactionType
import com.shire42.cnab.record.Record
import com.shire42.cnab.record.process.CnabRecordFile
import com.shire42.cnab.repository.TransactionRepository
import com.shire42.cnab.repository.TransactionTypeRepository
import org.springframework.stereotype.Service
import java.io.InputStream
import javax.transaction.Transactional

@Service
class TransactionService(
    private val transactionTypeRepository: TransactionTypeRepository,
    private val transactionRepository: TransactionRepository
) {

    @Transactional
    fun parserAndSaveTransactionFile(stream: InputStream): TransactionSummarizeRest {
        val list = parserToTransaction(stream)
        val transactions = transactionRepository.saveAll(list)
        val transactionsRest = parserTransactions(transactions)
        
        return TransactionSummarizeRest(
            transactions = transactionsRest,
            transactionAmount = summarizeTransactions(transactions)
        )
    }

    fun findTransactionsByStoreOwner(storeName: String): TransactionSummarizeRest {
        val transactions = this.transactionRepository.findByStoreName(storeName)
        val transactionsRest = parserTransactions(transactions)
        return TransactionSummarizeRest(
            transactions = transactionsRest,
            transactionAmount = summarizeTransactions(transactions)
        )
    }

    private fun summarizeTransactions(transactions: List<Transaction>): Double {
        var value: Double = 0.0

        transactions.forEach {
            if(it.type.signal == '+') {
                value += it.value
            } else {
                value -= it.value
            }
        }

        return value
    }

    private fun parserTransactions(transactions: List<Transaction>): List<TransactionRest> {
        val transactionsRest = ArrayList<TransactionRest>()

        transactions.forEach {
            val transactionRest = TransactionRest(
                data = it.data,
                ownerName = it.ownerName,
                storeName = it.storeName,
                value = it.value,
                cardNumber = it.cardNumber,
                cpf = it.cpf,
                transactionDescription = it.type.description,
                nature = it.type.nature
            )
            transactionsRest.add(transactionRest)
        }

        return transactionsRest
    }

    private fun parserToTransaction(stream: InputStream): List<Transaction> {
        val list = ArrayList<Transaction>()
        listRecords(stream).forEach {
            val transactionType = this.getTransactionType(it.findType())

            val transaction = Transaction(
                cpf = it.findCPF(),
                cardNumber = it.findCardNumber(),
                data = it.findData(),
                ownerName = it.findStoreOwner(),
                storeName = it.findStoreName(),
                value = it.findValue(),
                type = transactionType
            )
            list.add(transaction)
        }

        return list
    }

    private fun listRecords(stream: InputStream): List<Record> {
        val recordFile = CnabRecordFile(stream)
        return recordFile.createRecords()
    }

    private fun getTransactionType(type: Int): TransactionType {
        return transactionTypeRepository.findByType(type).get();
    }

}