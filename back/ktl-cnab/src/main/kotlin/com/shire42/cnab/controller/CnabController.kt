package com.shire42.cnab.controller

import com.shire42.cnab.controller.rest.CnabRest
import org.springframework.http.HttpStatus
import org.springframework.http.MediaType
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController
import org.springframework.web.multipart.MultipartFile
import java.util.*

@RestController
@RequestMapping(value = ["/cnab"], produces = [MediaType.APPLICATION_JSON_VALUE])
class CnabController {

    @PostMapping("/upload")
    fun uploadCnabFile(@RequestParam("file") multipart: MultipartFile): ResponseEntity<CnabRest> {
        return ResponseEntity(CnabRest(UUID.randomUUID()), HttpStatus.CREATED)
    }

}