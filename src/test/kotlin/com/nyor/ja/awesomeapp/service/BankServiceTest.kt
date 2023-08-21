package com.nyor.ja.awesomeapp.service

import com.nyor.ja.awesomeapp.datasource.BankDatasource
import io.mockk.mockk
import io.mockk.verify
import org.junit.jupiter.api.Test

internal class BankServiceTest {

    private val bankDatasource: BankDatasource = mockk(relaxed = true)
    private val bankService = BankService(bankDatasource)

    @Test
    fun `should call its datasource`() {

        bankService.getBanks()

        verify(exactly = 1) { bankDatasource.retrieveBanks() }
    }
}