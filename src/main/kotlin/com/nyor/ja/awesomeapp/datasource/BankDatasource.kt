package com.nyor.ja.awesomeapp.datasource

import com.nyor.ja.awesomeapp.model.Bank

interface BankDatasource {
    fun retrieveBanks(): Collection<Bank>

    fun retrieveBank(accountNumber: String): Bank
}