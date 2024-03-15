package com.leeheefull.api.board.controller.request

import com.fasterxml.jackson.annotation.JsonIgnore

data class CommentUpdateRequest(
    @JsonIgnore
    var commentId: Long = 0L,

    val content: String,
)
