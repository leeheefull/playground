package com.leeheefull.domain.board.repository

import com.leeheefull.domain.board.entity.Comment
import java.util.Optional
import org.springframework.data.jpa.repository.JpaRepository

interface CommentRepository : JpaRepository<Comment, Long> {
    fun findByIdAndDeletedIsFalse(id: Long): Optional<Comment>

    fun findByPostIdAndDeletedIsFalse(postId: Long): List<Comment>
}