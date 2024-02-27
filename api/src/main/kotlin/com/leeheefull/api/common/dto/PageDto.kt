package com.leeheefull.api.common.dto

data class PageDto<T>(
    val count: Long = 0L,
    val result: List<T>,
)
