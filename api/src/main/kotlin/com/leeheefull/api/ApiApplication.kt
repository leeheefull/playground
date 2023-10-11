package com.leeheefull.api

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication(
    scanBasePackages = [
        "com.leeheefull",
    ],
)
class ApiApplication

fun main(vararg args: String) {
    runApplication<ApiApplication>(*args)
}
