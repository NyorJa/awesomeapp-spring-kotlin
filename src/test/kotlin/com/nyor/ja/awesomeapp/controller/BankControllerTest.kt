package com.nyor.ja.awesomeapp.controller

import com.fasterxml.jackson.databind.ObjectMapper
import com.nyor.ja.awesomeapp.model.Bank
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Nested
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.TestInstance
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.http.MediaType
import org.springframework.test.web.servlet.*

@AutoConfigureMockMvc
@SpringBootTest
internal class BankControllerTest @Autowired constructor(
    val mockMvc: MockMvc,
    val objectMapper: ObjectMapper
) {

    val baseUrl = "/api/banks/"

    @Nested
    @DisplayName("getBanks()")
    @TestInstance(TestInstance.Lifecycle.PER_CLASS)
    inner class getBanks {
        @Test
        fun `should return all banks`() {
            mockMvc.get(baseUrl)
                .andDo { print() }
                .andExpect { status { isOk() } }
        }

    }

    @Nested
    @DisplayName("getBank()")
    @TestInstance(TestInstance.Lifecycle.PER_CLASS)
    inner class getBank {
        @Test
        fun `should return single bank`() {
            mockMvc.get(baseUrl + "1234")
                .andDo { print() }
                .andExpect { status { isOk() } }
        }

        @Test
        fun `should not return single bank`() {
            mockMvc.get(baseUrl + "12346")
                .andDo { print() }
                .andExpect { status { isNotFound() } }
        }
    }

    @Nested
    @DisplayName("createBank()")
    @TestInstance(TestInstance.Lifecycle.PER_CLASS)
    inner class createBank {

        @Test
        fun `it should create bank`() {

            val newBank = Bank("1234567", "piolo", 100.0, 100)

            mockMvc.post(baseUrl) {
                contentType = MediaType.APPLICATION_JSON
                content = objectMapper.writeValueAsString(newBank)
            }
                .andDo { print() }
                .andExpect { status { isCreated() } }
        }


        @Test
        fun `it should not create bank since it is existing`() {

            val newBank = Bank("1234", "piolo", 100.0, 100)

            mockMvc.post(baseUrl) {
                contentType = MediaType.APPLICATION_JSON
                content = objectMapper.writeValueAsString(newBank)
            }
                .andDo { print() }
                .andExpect { status { isBadRequest() } }
        }
    }


    @Nested
    @DisplayName("updateBank()")
    @TestInstance(TestInstance.Lifecycle.PER_CLASS)
    inner class updateBank {

        @Test
        fun `it should create bank`() {

            val newBank = Bank("1234", "piolo",100.0 ,100)

            mockMvc.put(baseUrl) {
                contentType = MediaType.APPLICATION_JSON
                content = objectMapper.writeValueAsString(newBank)
            }
                .andDo { print() }
                .andExpect { status { isOk() } }
        }


        @Test
        fun `it should not update bank since it is not existing`() {

            val newBank = Bank("123456789", "piolo",100.0, 100)

            mockMvc.put(baseUrl) {
                contentType = MediaType.APPLICATION_JSON
                content = objectMapper.writeValueAsString(newBank)
            }
                .andDo { print() }
                .andExpect { status { isNotFound() } }
        }
    }

    @Nested
    @DisplayName("deleteBank()")
    @TestInstance(TestInstance.Lifecycle.PER_CLASS)
    inner class deleteBank {

        @Test
        fun `it should delete bank`() {


            mockMvc.delete(baseUrl + "1234")
                .andDo { print() }
                .andExpect { status { isNoContent() } }
        }


        @Test
        fun `it should not update bank since it is not existing`() {

            mockMvc.delete(baseUrl + "12341231231")
                .andDo { print() }
                .andExpect { status { isNotFound() } }
        }
    }

}