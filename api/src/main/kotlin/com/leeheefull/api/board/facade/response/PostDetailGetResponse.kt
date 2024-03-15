package com.leeheefull.api.board.facade.response

import com.leeheefull.domain.board.entity.Post
import com.leeheefull.domain.enums.BoardType

data class PostDetailGetResponse(
    val postId: Long,
    val memberId: Long,
    val board: BoardType,
    val title: String,
    val content: String,
    val tags: List<String>,
    val viewCount: Long,
    var comments: List<CommentDetailGetResponse> = emptyList(),
) {

    companion object {
        infix fun from(post: Post): PostDetailGetResponse =
            PostDetailGetResponse(
                postId = post.id!!,
                memberId = post.memberId,
                board = post.board,
                title = post.title,
                content = post.content,
                tags = post.tags,
                viewCount = post.viewCount,
            )
    }
}
