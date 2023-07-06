package com.shire42.cnab.controller.user

import com.shire42.cnab.TestWebEnvironmentConfigTest
import org.junit.jupiter.api.Test
import org.springframework.http.MediaType
import org.springframework.test.web.servlet.post

class UserTest: TestWebEnvironmentConfigTest() {

    @Test
    fun `test add new user`() {
        mockMvc
            .post("/user") {
                content = """{
                        "username": "newuser@new.com",
                        "password": "newuser",
                        "firstName": "Guest", 
                        "lastName": "LastName"
                    }
                """.trimMargin()
                accept = MediaType.APPLICATION_JSON
                headers {
                    header("Content-Type", "application/json")
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

    @Test
    fun `should not add new user with username already in database`() {
        mockMvc
            .post("/user") {
                content = """{
                        "username": "superuser",
                        "password": "newuser",
                        "firstName": "Guest", 
                        "lastName": "LastName"
                    }
                """.trimMargin()
                accept = MediaType.APPLICATION_JSON
                headers {
                    header("Content-Type", "application/json")
                }
            }.andExpect {
                status { is5xxServerError() }
                content {
                    contentType(MediaType.APPLICATION_JSON)
                }
            }.andDo {
                print()
            }
    }

}