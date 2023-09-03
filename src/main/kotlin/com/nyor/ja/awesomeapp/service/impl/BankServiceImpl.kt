package com.nyor.ja.awesomeapp.service.impl

import com.nyor.ja.awesomeapp.datasource.BankRepository
import com.nyor.ja.awesomeapp.model.Bank
import com.nyor.ja.awesomeapp.service.BankService
import jakarta.persistence.EntityNotFoundException
import org.hibernate.service.spi.ServiceException
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.dao.DataIntegrityViolationException
import org.springframework.stereotype.Service
import java.lang.Exception

@Service
class BankServiceImpl @Autowired constructor(private val bankRepository: BankRepository) : BankService {
    companion object {
        const val MESSAGE_NOT_FOUND = "There is no member found with id: "
    }

    private val log: Logger = LoggerFactory.getLogger(this.javaClass)


    override fun findByAccountNumber(accountNumber: String): Bank {
        return bankRepository.findByAccountNumber(accountNumber).orElseThrow { EntityNotFoundException(MESSAGE_NOT_FOUND + accountNumber) }
    }

    override fun create(entity: Bank): Bank {
        try {
            return bankRepository.save(entity)
        } catch (e: Exception) {
            log.error("Error persisting a new Message: {}", e.message, e)
            throw ServiceException("Error persisting a new Message", e)
        }
    }

    override fun update(id: Long, entity: Bank): Bank {
        return entity
    }

    fun update(accountNumber: String, entity: Bank) : Bank {
        try {
            val persistedEntity = findByAccountNumber(accountNumber)
            updateFields(persistedEntity, entity)
            return bankRepository.save(persistedEntity);
        } catch (e: Exception) {
            log.error("Error updating a Message: {}", e.message, e)
            throw ServiceException("Error updating a Message", e)
        }
    }

    override fun findById(id: Long): Bank {
        return bankRepository.findById(id).orElseThrow { EntityNotFoundException(MESSAGE_NOT_FOUND + id) }
    }

    override fun findAll(): List<Bank> {
        try {
            val banks = bankRepository.findAll()
            return banks.toList()
        } catch (e: Exception) {
            log.error("Error retrieving all existing messages: {}", e.message, e)
            throw ServiceException("Error retrieving all existing messages", e)
        }
    }

    override fun deleteById(id: Long) {
        TODO("Not yet implemented")
    }

    fun deleteByAccountNumber(accountNumber: String) {
        val bank = findByAccountNumber(accountNumber)

        try {
            bank.id?.let { bankRepository.deleteById(it) }
        } catch(e: DataIntegrityViolationException) {
            log.error("Error deleting Message with id: " + accountNumber + " - " + e.message, e)
        }

    }

    private fun updateFields(persistedEntity: Bank, newEntity: Bank) {
        persistedEntity.trust = newEntity.trust
        persistedEntity.transactionFee = newEntity.transactionFee
        persistedEntity.name = newEntity.name
    }
}