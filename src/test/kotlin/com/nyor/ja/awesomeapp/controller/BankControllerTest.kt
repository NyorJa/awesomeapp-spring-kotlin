package com.nyor.ja.awesomeapp.controller

import com.nyor.ja.awesomeapp.service.impl.BankServiceImpl
import org.junit.jupiter.api.Disabled
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest
import org.springframework.boot.test.mock.mockito.MockBean
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.get


@WebMvcTest
@Disabled
class BankControllerTest {

    @Autowired
    private lateinit var mockMvc: MockMvc

    @MockBean
    private lateinit var service: BankServiceImpl

    @Test
    fun getAll() {
        mockMvc.get("/api/banks/").andDo { print() }
            .andExpect { status { isOk() } }
    }
}