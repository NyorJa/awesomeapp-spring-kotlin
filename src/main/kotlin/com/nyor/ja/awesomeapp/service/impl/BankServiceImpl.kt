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
        const val BANK_NOT_FOUND = "There is no bank found with id: "
    }

    private val log: Logger = LoggerFactory.getLogger(this.javaClass)


    override fun findByAccountNumber(accountNumber: String): Bank {
        return bankRepository.findByAccountNumber(accountNumber).orElseThrow { EntityNotFoundException(BANK_NOT_FOUND + accountNumber) }
    }

    override fun create(entity: Bank): Bank {
        try {
            return bankRepository.save(entity)
        } catch (e: Exception) {
            log.error("Error persisting a new Message: {}", e.message, e)
            throw ServiceException("Error persisting a new Bank", e)
        }
    }

    override fun update(id: Long, entity: Bank): Bank {
        try {
            val persistedEntity = findById(id)
            updateFields(persistedEntity, entity)
            return bankRepository.save(persistedEntity);
        } catch (e: Exception) {
            log.error("Error updating a Message: {}", e.message, e)
            throw IllegalArgumentException("Error updating a Bank", e)
        }    }

    fun update(accountNumber: String, entity: Bank) : Bank {
        try {
            val persistedEntity = findByAccountNumber(accountNumber)
            updateFields(persistedEntity, entity)
            return bankRepository.save(persistedEntity);
        } catch (e: Exception) {
            log.error("Error updating a Message: {}", e.message, e)
            throw IllegalArgumentException("Error updating a Bank", e)
        }
    }

    override fun findById(id: Long): Bank {
        return bankRepository.findById(id).orElseThrow { EntityNotFoundException(BANK_NOT_FOUND + id) }
    }

    override fun findAll(): List<Bank> {
        return bankRepository.findAll().toList()
    }

    override fun deleteById(id: Long) {
        try {
            bankRepository.deleteById(id)
        } catch(e: DataIntegrityViolationException) {
            log.error("Error deleting Bank with id: " + id + " - " + e.message, e)
            throw ServiceException("Error retrieving all existing banks", e)
        }
    }

    fun deleteByAccountNumber(accountNumber: String) {
        try {
            val bank = findByAccountNumber(accountNumber)
            bank.id?.let { deleteById(it) }
        } catch(e: EntityNotFoundException) {
            log.error("Error deleting Bank with id: " + accountNumber + " - " + e.message, e)
            throw e
        }

    }

    private fun updateFields(persistedEntity: Bank, newEntity: Bank) {
        persistedEntity.trust = newEntity.trust
        persistedEntity.transactionFee = newEntity.transactionFee
        persistedEntity.name = newEntity.name
    }
}