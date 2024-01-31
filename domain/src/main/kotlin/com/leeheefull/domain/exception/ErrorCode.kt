package com.leeheefull.domain.exception

enum class ErrorCode(
    val code: String,
    val message: String,
) {
    /**
     * Unchecked error
     */
    UNEXPECTED_ERROR("U0001", "예상하지 못한 에러입니다."),

    /**
     * Convert exception
     */
    OBJECT_TO_STRING_CONVERT_FAIL_MESSAGE("C0001", "객체를 문자열로 변환하는데 실패하였습니다."),
    OBJECT_LIST_TO_STRING_CONVERT_FAIL_MESSAGE("C0002", "객체 리스트를 문자열로 변환하는데 실패하였습니다."),
    OBJECT_FORM_INCORRECT("C0003", "객체의 형태가 옳바르지 않습니다."),
    OBJECT_LIST_FORM_INCORRECT("C0004", "객체 리스트 형태가 옳바르지 않습니다."),

    /**
     * Entity not found error
     */
    POST_NOT_EXIST("E0001", "게시글이 존재하지 않습니다."),
    COMMENT_NOT_EXIST("E0002", "댓글이 존재하지 않습니다."),
}
