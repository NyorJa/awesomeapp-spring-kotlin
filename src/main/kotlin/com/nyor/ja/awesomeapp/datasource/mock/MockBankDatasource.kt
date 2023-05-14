package com.nyor.ja.awesomeapp.datasource.mock

import com.nyor.ja.awesomeapp.datasource.BankDatasource
import com.nyor.ja.awesomeapp.model.Bank
import org.springframework.stereotype.Repository

@Repository
class MockBankDatasource : BankDatasource {

    val banks = listOf(
        Bank("1234", 0.0, 1),
        Bank("5676", 0.1, 2),
        Bank("89010", 0.2, 3)
    )

    override fun retrieveBanks(): Collection<Bank> = banks
}