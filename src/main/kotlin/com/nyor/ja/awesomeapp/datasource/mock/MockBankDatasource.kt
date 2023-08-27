package com.nyor.ja.awesomeapp.datasource.mock

import com.nyor.ja.awesomeapp.datasource.BankDatasource
import com.nyor.ja.awesomeapp.model.Bank
import org.springframework.stereotype.Repository

@Repository
class MockBankDatasource : BankDatasource {

    val banks = mutableListOf(
        Bank("1234", "piolo",0.0, 1),
        Bank("5676", "sam",0.1, 2),
        Bank("89010", "robin", 0.2, 3)
    )

    override fun retrieveBanks(): Collection<Bank> = banks
    override fun retrieveBank(accountNumber: String): Bank {
        return banks.firstOrNull() { bank -> bank.accountNumber == accountNumber }
            ?: throw NoSuchElementException("Could not find bank account number $accountNumber")
    }

    override fun createBank(bank: Bank): Bank {
        if (banks.any { it.accountNumber == bank.accountNumber }) {
            throw IllegalArgumentException("Account number: ${bank.accountNumber} is existing")
        }
        banks.add(bank)
        return bank
    }

    override fun updateBank(bank: Bank): Bank {
        val currentBank = retrieveBank(bank.accountNumber)

        banks.remove(currentBank)
        banks.add(bank)

        return bank
    }

    override fun deleteBank(accountNumber: String) {
        val currentBank = retrieveBank(accountNumber)
        banks.remove(currentBank)
    }
}