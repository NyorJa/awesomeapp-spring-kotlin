package com.nyor.ja.awesomeapp.controller

import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Nested
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.TestInstance
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.get

@AutoConfigureMockMvc
@SpringBootTest
internal class BankControllerTest {
    @Autowired
    lateinit var mockMvc: MockMvc

    @Nested
    @DisplayName("getBanks()")
    @TestInstance(TestInstance.Lifecycle.PER_CLASS)
    inner class getBanks {
        @Test
        fun `should return all banks`() {
            mockMvc.get("/api/banks")
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
            mockMvc.get("/api/banks/1234")
                    .andDo { print() }
                    .andExpect { status { isOk() } }
        }

        @Test
        fun `should not return single bank`() {
            mockMvc.get("/api/banks/12346")
                    .andDo { print() }
                    .andExpect { status { isNotFound() } }
        }
    }

}