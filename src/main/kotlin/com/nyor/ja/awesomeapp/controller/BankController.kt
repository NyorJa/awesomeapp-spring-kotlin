package com.nyor.ja.awesomeapp.controller

import com.nyor.ja.awesomeapp.model.Bank
import com.nyor.ja.awesomeapp.service.BankService
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/api/banks/")
class BankController(private val service: BankService) {

    @ExceptionHandler(IllegalArgumentException::class)
    fun handlerBadRequest(e: IllegalArgumentException): ResponseEntity<String> =
        ResponseEntity(e.message, HttpStatus.BAD_REQUEST)

    @ExceptionHandler(NoSuchElementException::class)
    fun handlerNotFound(e: NoSuchElementException): ResponseEntity<String> =
        ResponseEntity(e.message, HttpStatus.NOT_FOUND)

    @GetMapping
    fun getBanks(): Collection<Bank> = service.getBanks()

    @GetMapping("{accountNumber}")
    fun getBank(@PathVariable accountNumber: String): ResponseEntity<Bank> =
        ResponseEntity.ok(service.getBank(accountNumber))

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    fun addBank(@RequestBody bank: Bank): Bank = service.createBank(bank)
}