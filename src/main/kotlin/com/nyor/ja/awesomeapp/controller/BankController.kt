package com.nyor.ja.awesomeapp.controller

import com.nyor.ja.awesomeapp.model.Bank
import com.nyor.ja.awesomeapp.service.BankService
import com.nyor.ja.awesomeapp.service.impl.BankServiceImpl
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/api/banks/")
class BankController(private val service: BankServiceImpl) {

/*    @ExceptionHandler(IllegalArgumentException::class)
    fun handlerBadRequest(e: IllegalArgumentException): ResponseEntity<String> =
        ResponseEntity(e.message, HttpStatus.BAD_REQUEST)

    @ExceptionHandler(NoSuchElementException::class)
    fun handlerNotFound(e: NoSuchElementException): ResponseEntity<String> =
        ResponseEntity(e.message, HttpStatus.NOT_FOUND)*/

    @GetMapping
    fun getBanks(): Collection<Bank> = service.findAll()

    @GetMapping("{accountNumber}")
    fun getBank(@PathVariable accountNumber: String): ResponseEntity<Bank> =
        ResponseEntity.ok(service.findByAccountNumber(accountNumber))

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    fun addBank(@RequestBody bank: Bank): Bank = service.create(bank)

    @PutMapping
    fun updateBank(@RequestBody bank: Bank): Bank = service.update(bank.accountNumber!!, bank)

    @DeleteMapping("{accountNumber}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    fun deleteBank(@PathVariable accountNumber: String): Unit = service.deleteByAccountNumber(accountNumber)
}