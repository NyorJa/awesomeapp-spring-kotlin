package com.nyor.ja.awesomeapp.datasource

import com.nyor.ja.awesomeapp.model.Bank
import org.springframework.data.repository.CrudRepository
import org.springframework.stereotype.Repository
import java.util.*

@Repository
interface BankRepository : CrudRepository<Bank, Long> {

    fun findByAccountNumber(accountNumber: String) : Optional<Bank>
}