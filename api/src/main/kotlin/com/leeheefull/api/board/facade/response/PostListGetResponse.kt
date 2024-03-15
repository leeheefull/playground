package com.leeheefull.api.board.facade.response

import com.leeheefull.domain.board.query.result.PostSearchResult
import com.leeheefull.domain.enums.BoardType

data class PostListGetResponse(
    val postId: Long,
    val memberId: Long,
    val board: BoardType,
    val title: String,
    val content: String,
    val tags: List<String>? = null,
    val viewCount: Long,
) {
    companion object {
        infix fun from(result: PostSearchResult): PostListGetResponse =
            PostListGetResponse(
                postId = result.postId,
                memberId = result.memberId,
                board = result.board,
                title = result.title,
                content = result.content,
                tags = result.tags,
                viewCount = result.viewCount,
            )
    }
}
