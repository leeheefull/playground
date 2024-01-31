package com.leeheefull.domain.board.service

import com.leeheefull.domain.board.entity.Post
import com.leeheefull.domain.board.repository.PostRepository
import com.leeheefull.domain.enums.BoardType
import com.leeheefull.domain.exception.EntityNotFoundException
import com.leeheefull.domain.exception.ErrorCode.POST_NOT_EXIST
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class PostService(
    private val postRepository: PostRepository,
) {
    @Transactional
    fun create(
        memberId: Long,
        board: BoardType,
        title: String,
        content: String,
        tags: List<String>?,
    ) {
        Post(
            memberId = memberId,
            board = board,
            title = title,
            content = content,
            tags = tags,
        ).also { postRepository.save(it) }
    }

    @Transactional
    fun view(id: Long) {
        find(id).view()
    }

    @Transactional(readOnly = true)
    fun find(id: Long): Post =
        postRepository.findByIdAndDeletedIsFalse(id)
            .orElseThrow { throw EntityNotFoundException(POST_NOT_EXIST) }

    @Transactional
    fun update(
        id: Long,
        title: String?,
        content: String?,
        tags: List<String>?,
    ) {
        find(id).update(
            title = title,
            content = content,
            tags = tags,
        )
    }

    @Transactional
    fun delete(id: Long) {
        find(id).delete()
    }
}