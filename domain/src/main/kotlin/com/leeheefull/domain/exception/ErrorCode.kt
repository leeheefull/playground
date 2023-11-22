package com.leeheefull.domain.exception

enum class ErrorCode(
    val code: String,
    val message: String,
) {
    /**
     * Unchecked error
     */
    UNEXPECTED_ERROR("U0001", "예상하지 못한 에러입니다."),
}
