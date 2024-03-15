package com.leeheefull.domain.board.repository

import com.leeheefull.domain.board.entity.Post
import java.util.Optional
import org.springframework.data.jpa.repository.JpaRepository

interface PostRepository : JpaRepository<Post, Long> {
    fun findByIdAndDeletedIsFalse(id: Long): Optional<Post>
}