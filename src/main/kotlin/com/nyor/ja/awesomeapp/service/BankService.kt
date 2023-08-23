package com.nyor.ja.awesomeapp.service

import com.nyor.ja.awesomeapp.datasource.BankDatasource
import com.nyor.ja.awesomeapp.model.Bank
import org.springframework.stereotype.Service

@Service
class BankService(private val datasource: BankDatasource) {
    fun getBanks(): Collection<Bank> = datasource.retrieveBanks()

    fun getBank(accountNumber: String): Bank = datasource.retrieveBank(accountNumber);

    fun createBank(bank: Bank) = datasource.createBank(bank)

    fun updateBank(bank: Bank) = datasource.updateBank(bank)

    fun deleteBank(accountNumber: String) = datasource.deleteBank(accountNumber)
}