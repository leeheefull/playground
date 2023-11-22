package com.leeheefull.api.exception

import com.leeheefull.domain.exception.BusinessException
import com.leeheefull.domain.exception.ErrorCode.UNEXPECTED_ERROR
import mu.KotlinLogging
import org.springframework.http.HttpStatus.BAD_REQUEST
import org.springframework.http.HttpStatus.INTERNAL_SERVER_ERROR
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.RestControllerAdvice

@RestControllerAdvice
class GlobalExceptionHandler {
    private val logger = KotlinLogging.logger {}

    @ExceptionHandler(BusinessException::class)
    protected fun handleBusinessException(e: BusinessException): ResponseEntity<ErrorResponse> =
        ResponseEntity.badRequest()
            .body(
                ErrorResponse(
                    code = e.errorCode.code,
                    message = e.errorCode.message,
                    status = BAD_REQUEST,
                    errors = e.stackTrace.map { it.toString() }
                )
            )
            .also {
                val description = if (e.description != "") "[${e.description}]" else ""
                logger.info("BusinessException : ${e.errorCode.message} $description", e)
            }

    @ExceptionHandler(Exception::class)
    protected fun handleUncheckedException(e: Exception): ResponseEntity<ErrorResponse> =
        ResponseEntity.status(INTERNAL_SERVER_ERROR)
            .body(
                ErrorResponse(
                    code = UNEXPECTED_ERROR.code,
                    message = UNEXPECTED_ERROR.message,
                    status = INTERNAL_SERVER_ERROR,
                    errors = e.stackTrace.map { it.toString() }
                )
            )
            .also {
                logger.error("UncheckedException", e)
            }
}
