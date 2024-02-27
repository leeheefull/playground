package com.leeheefull.api.board.facade.response

import com.leeheefull.domain.board.entity.Comment

data class CommentDetailGetResponse(
    val commentId: Long,
    val memberId: Long,
    val content: String,
) {
    companion object {
        infix fun from(comment: Comment): CommentDetailGetResponse =
            CommentDetailGetResponse(
                commentId = comment.id!!,
                memberId = comment.memberId,
                content = comment.content
            )
    }
}
