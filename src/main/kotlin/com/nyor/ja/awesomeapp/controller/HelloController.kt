package com.nyor.ja.awesomeapp.controller

import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api/hello")
class HelloController {
    @GetMapping
    fun helloWorld(): String = "hello again"
}