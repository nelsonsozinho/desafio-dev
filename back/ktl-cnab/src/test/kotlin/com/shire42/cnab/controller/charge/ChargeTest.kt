package com.shire42.cnab.controller.charge

import com.shire42.cnab.TestEnvironmentConfigTest
import org.junit.jupiter.api.Test
import org.springframework.http.MediaType
import org.springframework.test.web.servlet.get
import org.springframework.test.web.servlet.post


class ChargeTest: TestEnvironmentConfigTest() {


//    @Test
//    fun `test get charge by id`() {
//        mockMvc
//            .get("/charge/{id}", "9a65975c-8490-4856-b757-db0564a5215d") {
//                headers {
//                    header("Authorization", getUserToken()!!)
//                    header("Content-Type", "application/json")
//                }
//            }.andExpect {
//                status { isOk() }
//                content {
//                    contentType(MediaType.APPLICATION_JSON)
//                }
//            }.andDo {
//                print()
//            }
//    }
//
//    @Test
//    fun `list all charges`() {
//        mockMvc
//            .get("/charge") {
//                headers {
//                    header("Authorization", getUserToken()!!)
//                    header("Content-Type", "application/json")
//                }
//            }.andExpect {
//                status { isOk() }
//                content {contentType(MediaType.APPLICATION_JSON)}
//                jsonPath("$") {
//                    isNotEmpty()
//                }
//            }.andDo {
//                print()
//            }
//    }
//
//    @Test
//    fun `add new charge with success`() {
//        mockMvc
//            .post("/charge") {
//                content = """{
//                        "amount": 40.90,
//                        "description": "test",
//                        "tag": "test tag"
//                    }
//                """.trimMargin()
//                accept = MediaType.APPLICATION_JSON
//                headers {
//                    header("Authorization", getUserToken()!!)
//                    header("Content-Type", "application/json")
//                }
//            }.andExpect {
//                status { isCreated() }
//                content {contentType(MediaType.APPLICATION_JSON)}
//                jsonPath("$") {
//                    isNotEmpty()
//                }
//            }.andDo {
//                print()
//            }
//    }
//
//    @Test
//    fun `users does not have access another charges`() {
//        val chargeFromJohnDoeId = "ba1fad1c-ab94-4ba4-b34d-a28c632078b3"
//
//        mockMvc
//            .get("/charge/{id}", chargeFromJohnDoeId) {
//                headers {
//                    header("Authorization", getUserToken()!!)
//                    header("Content-Type", "application/json")
//                }
//            }.andExpect {
//                status { isNotFound() }
//                content {
//                    contentType(MediaType.APPLICATION_JSON)
//                }
//            }.andDo {
//                print()
//            }
//    }

}