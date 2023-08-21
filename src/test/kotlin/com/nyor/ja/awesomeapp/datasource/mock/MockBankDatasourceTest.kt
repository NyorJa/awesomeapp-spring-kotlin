package com.nyor.ja.awesomeapp.datasource.mock

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class MockBankDatasourceTest {

    private val mockBankDatasource = MockBankDatasource()

    @Test
    fun `should provide collection of banks`() {
        val banks = mockBankDatasource.retrieveBanks()

        assertThat(banks).isNotEmpty()
    }

    @Test
    fun `should provide some mock data`() {
        val banks = mockBankDatasource.retrieveBanks()

        assertThat(banks).allMatch { it.accountNumber.isNotBlank() }
        assertThat(banks).anyMatch { it.trust != 0.0 }
        assertThat(banks).anyMatch { it.transactionFee != 0 }
    }
}