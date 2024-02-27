package com.leeheefull.domain.board.service

import com.leeheefull.domain.board.entity.Comment
import com.leeheefull.domain.board.repository.CommentRepository
import com.leeheefull.domain.exception.EntityNotFoundException
import com.leeheefull.domain.exception.ErrorCode.COMMENT_NOT_EXIST
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class CommentService(
    private val commentRepository: CommentRepository,
) {
    @Transactional
    fun create(
        memberId: Long,
        postId: Long,
        content: String,
    ) {
        Comment(
            memberId = memberId,
            postId = postId,
            content = content,
        ).also { commentRepository.save(it) }
    }

    @Transactional(readOnly = true)
    fun find(id: Long): Comment =
        commentRepository.findByIdAndDeletedIsFalse(id)
            .orElseThrow { throw EntityNotFoundException(COMMENT_NOT_EXIST) }

    @Transactional(readOnly = true)
    fun findByPostId(postId: Long): List<Comment> =
        commentRepository.findByPostIdAndDeletedIsFalse(postId)

    @Transactional
    fun update(id: Long, content: String?) {
        find(id).update(content)
    }

    @Transactional
    fun delete(id: Long) {
        find(id).delete()
    }

    @Transactional
    fun deleteByPostId(postId: Long) {
        findByPostId(postId).forEach { commentRepository.delete(it) }
    }
}