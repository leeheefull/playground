package com.leeheefull.domain.util

import com.fasterxml.jackson.module.kotlin.jacksonObjectMapper
import com.fasterxml.jackson.module.kotlin.readValue
import com.leeheefull.domain.exception.ErrorCode
import com.leeheefull.domain.exception.ErrorCode.OBJECT_FORM_INCORRECT
import com.leeheefull.domain.exception.ErrorCode.OBJECT_LIST_FORM_INCORRECT
import com.leeheefull.domain.exception.ErrorCode.OBJECT_TO_STRING_CONVERT_FAIL_MESSAGE
import com.leeheefull.domain.exception.JsonConvertException

object JsonUtil {
    /**
     * 객체를 문자열로 변환
     * @param obj 제네릭 타입을 갖는 객체
     * @return 객체를 문자열로 직렬화한 결과
     * @throws JsonConvertException
     */
    inline fun <reified T> convertObjectToString(obj: T): String? {
        return obj?.let {
            runCatching { jacksonObjectMapper().writeValueAsString(obj) }
                .getOrElse { throw JsonConvertException(OBJECT_TO_STRING_CONVERT_FAIL_MESSAGE) }
        }
    }

    /**
     * 객체를 문자열로 변환
     * @param objs 제네릭 타입을 갖는 객체 리스트
     * @return 객체 리스트를 문자열로 직렬화한 결과
     * @throws JsonConvertException
     */
    inline fun <reified T> convertObjectListToString(objs: List<T>?): String? {
        return objs?.let {
            runCatching { jacksonObjectMapper().writeValueAsString(objs) }
                .getOrElse { throw JsonConvertException(ErrorCode.OBJECT_LIST_TO_STRING_CONVERT_FAIL_MESSAGE) }
        }
    }

    /**
     * json 구조를 갖는 문자열을 객체로 변환
     * @param json json 구조를 갖고 있는 문자열
     * @return 문자열울 객체로 역직렬화한 결과
     * @throws JsonConvertException
     */
    inline fun <reified T> convertJsonToObject(json: String?): T? {
        return json?.let {
            runCatching { jacksonObjectMapper().readValue(json, T::class.java) }
                .getOrElse { throw JsonConvertException(OBJECT_FORM_INCORRECT) }
        }
    }

    /**
     * json 구조를 갖는 문자열 리스트를 객체로 변환
     * @param jsonArray json 구조를 갖고 있는 문자열 리스트
     * @return 문자열 리스트를 객체 리스트로 역직렬화한 결과
     * @throws JsonConvertException
     */
    inline fun <reified T> convertJsonArrayToObjectList(jsonArray: String?): List<T>? {
        return jsonArray?.takeIf { it.isNotBlank() }
            ?.runCatching { jacksonObjectMapper().readValue<List<T>>(this) }
            ?.getOrElse { throw JsonConvertException(OBJECT_LIST_FORM_INCORRECT) }
    }
}