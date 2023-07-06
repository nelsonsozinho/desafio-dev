package com.shire42.cnab.controller

import com.shire42.cnab.controller.rest.CnabRest
import com.shire42.cnab.controller.rest.TransactionRest
import com.shire42.cnab.controller.rest.TransactionSummarizeRest
import com.shire42.cnab.service.TransactionService
import org.springframework.http.HttpStatus
import org.springframework.http.MediaType
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestAttribute
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController
import org.springframework.web.multipart.MultipartFile
import java.util.*

@RestController
@RequestMapping(value = ["/cnab"], produces = [MediaType.APPLICATION_JSON_VALUE])
class CnabController(private val service: TransactionService) {

    @PostMapping("/upload")
    fun uploadCnabFile(@RequestParam("file") multipart: MultipartFile): ResponseEntity<TransactionSummarizeRest> {
        return ResponseEntity(service.parserAndSaveTransactionFile(multipart.inputStream), HttpStatus.OK)

    }

    @GetMapping("/find")
    fun findTransactions(@RequestParam("ownerName") ownerName: String): ResponseEntity<TransactionSummarizeRest> {
        return ResponseEntity(service.findTransactionsByStoreOwner(ownerName), HttpStatus.OK)
    }

}