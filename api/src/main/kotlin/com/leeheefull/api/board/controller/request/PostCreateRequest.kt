package com.leeheefull.api.board.controller.request

import com.leeheefull.domain.enums.BoardType

data class PostCreateRequest(
    var memberId: Long = 0L,

    val board: BoardType,

    val title: String,

    val content: String,

    val tags: List<String> = emptyList(),
)