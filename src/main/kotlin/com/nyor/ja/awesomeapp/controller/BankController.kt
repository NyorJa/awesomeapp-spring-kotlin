package com.nyor.ja.awesomeapp.controller

import com.nyor.ja.awesomeapp.model.Bank
import com.nyor.ja.awesomeapp.service.impl.BankServiceImpl
import jakarta.persistence.EntityNotFoundException
import org.hibernate.service.spi.ServiceException
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/api/banks/")
class BankController(private val service: BankServiceImpl) {

    @ExceptionHandler(IllegalArgumentException::class)
    fun handlerBadRequest(e: IllegalArgumentException): ResponseEntity<String> =
        ResponseEntity(e.message, HttpStatus.BAD_REQUEST)

    @ExceptionHandler(ServiceException::class)
    fun handleDuplicate(e: ServiceException): ResponseEntity<String> =
        ResponseEntity(e.message, HttpStatus.BAD_REQUEST)

    @ExceptionHandler(NoSuchElementException::class)
    fun handlerNotFound(e: NoSuchElementException): ResponseEntity<String> =
        ResponseEntity(e.message, HttpStatus.NOT_FOUND)

    @ExceptionHandler(EntityNotFoundException::class)
    fun handlerNotFound(e: EntityNotFoundException): ResponseEntity<String> =
        ResponseEntity(e.message, HttpStatus.NOT_FOUND)

    @GetMapping
    fun getBanks(): Collection<Bank> = service.findAll()

    @GetMapping("/accountNumber/{accountNumber}")
    fun getBankByAccountNumber(@PathVariable accountNumber: String): ResponseEntity<Bank> =
        ResponseEntity.ok(service.findByAccountNumber(accountNumber))

    @GetMapping("/{id}")
    fun getBankById(@PathVariable id: Long): ResponseEntity<Bank> = ResponseEntity.ok(service.findById(id))

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    fun addBank(@RequestBody bank: Bank): Bank = service.create(bank)

    @PutMapping("/{id}")
    fun updateBank(@PathVariable id: Long, @RequestBody bank: Bank): Bank = service.update(id, bank)

    @DeleteMapping("/accountNumber/{accountNumber}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    fun deleteBankByAccountNumber(@PathVariable accountNumber: String): Unit = service.deleteByAccountNumber(accountNumber)

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    fun deleteBankById(@PathVariable id: Long): Unit = service.deleteById(id)
}