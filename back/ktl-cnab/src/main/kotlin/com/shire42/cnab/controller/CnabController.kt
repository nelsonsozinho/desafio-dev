package com.shire42.cnab.controller

import com.shire42.cnab.controller.rest.TransactionSummarizeRest
import com.shire42.cnab.service.TransactionService
import io.swagger.annotations.Api
import io.swagger.annotations.ApiOperation
import org.springframework.http.HttpStatus
import org.springframework.http.MediaType
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import org.springframework.web.multipart.MultipartFile
import java.util.*

@RestController
@RequestMapping(value = ["/cnab"], produces = [MediaType.APPLICATION_JSON_VALUE])
@Api(value = "API used to keep the CNAB transactions.",
    description = "This API provides the capability to search Student from a Student Repository", produces = "application/json")
class CnabController(private val service: TransactionService) {

    @PostMapping("/upload")
    @ApiOperation(value = "Upload CNAB file with the transactions ", produces = "application/json")
    fun uploadCnabFile(@RequestParam("file") multipart: MultipartFile): ResponseEntity<TransactionSummarizeRest> {
        return ResponseEntity(service.parserAndSaveTransactionFile(multipart.inputStream), HttpStatus.CREATED)

    }

    @GetMapping("/find")
    @ApiOperation(value = "Search", produces = "application/json")
    fun findTransactions(@RequestParam("ownerName") ownerName: String): ResponseEntity<TransactionSummarizeRest> {
        return ResponseEntity(service.findTransactionsByStoreOwner(ownerName), HttpStatus.OK)
    }

}