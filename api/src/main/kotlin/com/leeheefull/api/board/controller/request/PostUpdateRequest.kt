package com.leeheefull.api.board.controller.request

import com.fasterxml.jackson.annotation.JsonIgnore

data class PostUpdateRequest(
    @JsonIgnore
    var postId: Long = 0L,

    val title: String? = null,

    val content: String? = null,

    val tags: List<String>? = null,
)