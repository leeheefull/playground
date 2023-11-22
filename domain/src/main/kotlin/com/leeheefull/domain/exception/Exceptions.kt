package com.leeheefull.domain.exception

open class BusinessException(
    val errorCode: ErrorCode,
    val description: String = "",
) : RuntimeException(errorCode.message)