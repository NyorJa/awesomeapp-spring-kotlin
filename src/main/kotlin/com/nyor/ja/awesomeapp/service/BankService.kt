package com.nyor.ja.awesomeapp.service

import com.nyor.ja.awesomeapp.model.Bank

interface BankService : BaseService<Bank> {
    fun findByAccountNumber(accountNumber: String) : Bank
}