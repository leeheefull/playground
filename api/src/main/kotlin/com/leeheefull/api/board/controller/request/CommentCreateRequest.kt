package com.leeheefull.api.board.controller.request

import com.fasterxml.jackson.annotation.JsonIgnore

data class CommentCreateRequest(
    @JsonIgnore
    var postId: Long = 0L,

    var memberId: Long = 0L,

    val content: String,
)
