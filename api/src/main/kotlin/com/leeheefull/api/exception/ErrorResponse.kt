package com.leeheefull.api.exception

import org.springframework.http.HttpStatus

data class ErrorResponse(
    val code: String = "",
    val message: String = "",
    val status: HttpStatus? = null,
    val errors: List<String> = emptyList(),
)
