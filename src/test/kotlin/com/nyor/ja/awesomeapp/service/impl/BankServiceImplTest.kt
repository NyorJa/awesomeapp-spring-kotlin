package com.nyor.ja.awesomeapp.service.impl

import com.nyor.ja.awesomeapp.model.Bank
import jakarta.persistence.EntityNotFoundException
import org.hibernate.service.spi.ServiceException
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Disabled
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest

@SpringBootTest
@Disabled
class BankServiceImplTest {

    @Autowired
    private lateinit var bankServiceImpl: BankServiceImpl

    @Test
    fun testFindByAccountNumber() {
        val actual = bankServiceImpl.findByAccountNumber("1234");

        assertEquals("1234", actual.accountNumber)
    }


    @Test
    fun testFindByAccountNumberNotExisting() {
        val exception = assertThrows(EntityNotFoundException::class.java) {
            bankServiceImpl.findByAccountNumber("12346");
        }
        assertEquals("There is no bank found with id: 12346", exception.message)
    }

    @Test
    fun testCreate() {
        val bank = Bank()
        bank.accountNumber = "1234567"
        bank.name = "sammy"
        bank.trust = 1.0
        bank.transactionFee = 1

        val actual = bankServiceImpl.create(bank)

        assertNotNull(actual)
    }


    @Test
    fun testCreateWithExistingAccount() {
        val bank = Bank()
        bank.accountNumber = "1234"
        bank.name = "sammy"
        bank.trust = 1.0
        bank.transactionFee = 1

        val exception = assertThrows(ServiceException::class.java) {
            bankServiceImpl.create(bank)
        }

        assertEquals("Error persisting a new Bank", exception.message)
    }

    @Test
    fun testUpdate() {
        val bank = Bank()
        bank.accountNumber = "1234"
        bank.name = "sammy"
        bank.trust = 1.0
        bank.transactionFee = 1
        val actual = bankServiceImpl.update(1L, bank)
        assertNotNull(actual)
    }

    @Test
    fun testUpdate2() {
        val bank = Bank()
        bank.accountNumber = "1234"
        bank.name = "sammy"
        bank.trust = 1.1
        bank.transactionFee = 1
        val actual = bankServiceImpl.update("1234", bank)
        assertNotNull(actual)
        assertEquals(1.1, bank.trust)
    }

    @Test
    fun testUpdate3() {
        val bank = Bank()
        val exception = assertThrows(IllegalArgumentException::class.java) {
            bankServiceImpl.update("1234dsadas", bank)
        }

        assertEquals("Error updating a Bank", exception.message)
    }

    @Test
    fun testFindById() {
        val actual = bankServiceImpl.findById(1L)
        assertNotNull(actual)
    }


    @Test
    fun testFindAll() {
        assertNotNull(bankServiceImpl.findAll())
    }

    @Test
    fun testDeleteByAccountNumber() {
         bankServiceImpl.deleteByAccountNumber("1234")
    }


    @Test
    fun testDeleteByAccountNumber2() {
        assertThrows(EntityNotFoundException::class.java) {
            bankServiceImpl.deleteByAccountNumber("1234dsadas")
        }
    }
}