package com.leeheefull.domain.exception

open class BusinessException(
    val errorCode: ErrorCode,
    val description: String = "",
) : RuntimeException(errorCode.message)

class EntityNotFoundException(errorCode: ErrorCode, description: String = "") :
    BusinessException(errorCode, description)

class JsonConvertException(errorCode: ErrorCode, description: String = "") :
    BusinessException(errorCode, description)