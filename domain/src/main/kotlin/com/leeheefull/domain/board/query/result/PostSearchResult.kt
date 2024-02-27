package com.leeheefull.domain.board.query.result

import com.leeheefull.domain.enums.BoardType

/**
 * QueryDsl Projection Class(수정 주의)
 */
data class PostSearchResult(
    val postId: Long,
    val memberId: Long,
    val board: BoardType,
    val title: String,
    val content: String,
    val tags: List<String>? = null,
    val viewCount: Long,
)