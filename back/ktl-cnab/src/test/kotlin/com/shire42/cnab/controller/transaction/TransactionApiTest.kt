package com.shire42.cnab.controller.transaction

import com.shire42.cnab.TestWebEnvironmentConfigTest
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.springframework.http.MediaType
import org.springframework.mock.web.MockMultipartFile
import org.springframework.test.web.servlet.get
import org.springframework.test.web.servlet.multipart
import org.testcontainers.shaded.com.google.common.io.Resources
import java.io.FileInputStream
import java.io.InputStream

class TransactionApiTest: TestWebEnvironmentConfigTest() {


    var file: InputStream? = null


    @BeforeEach
    fun setup() {
        val url = Resources.getResource("cnab/CNAB.txt")
        this.file = FileInputStream(url.file)
    }

    @Test
    fun `test find transaction by name`() {
        val token = getUserToken()
        mockMvc
            .get("/cnab/find?ownerName=MERCADO DA AVENIDA") {
                accept = MediaType.APPLICATION_JSON
                headers {
                    header("Content-Type", "application/json")
                    header("Authorization", token!!)
                }
            }.andExpect {
                status { isOk() }
                content {
                    contentType(MediaType.APPLICATION_JSON)
                }
            }.andDo {
                print()
            }
    }

    @Test
    fun `should upload the file to upload api`() {
        val token = getUserToken()
        val mockup = MockMultipartFile("file", file!!)

        mockMvc
            .multipart("/cnab/upload") {
                file(mockup)
                headers {
                    header("Content-Type", "multipart/form-data; boundary=--------------------------913154542969906537697351")
                    header("Authorization", token!!)
                }
            }.andExpect {
                status { isCreated() }
                content {
                    contentType(MediaType.APPLICATION_JSON)
                }
            }.andDo {
                print()
            }
    }

}