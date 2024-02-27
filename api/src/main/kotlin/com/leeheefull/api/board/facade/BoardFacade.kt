package com.leeheefull.api.board.facade

import com.leeheefull.api.board.controller.request.CommentCreateRequest
import com.leeheefull.api.board.controller.request.CommentUpdateRequest
import com.leeheefull.api.board.controller.request.PostCreateRequest
import com.leeheefull.api.board.controller.request.PostListGetParams
import com.leeheefull.api.board.controller.request.PostUpdateRequest
import com.leeheefull.api.board.facade.response.CommentDetailGetResponse
import com.leeheefull.api.board.facade.response.PostDetailGetResponse
import com.leeheefull.api.board.facade.response.PostListGetResponse
import com.leeheefull.api.common.dto.PageDto
import com.leeheefull.domain.board.query.BoardQueryRepository
import com.leeheefull.domain.board.service.CommentService
import com.leeheefull.domain.board.service.PostService
import org.springframework.stereotype.Component
import org.springframework.transaction.annotation.Transactional

@Component
class BoardFacade(
    private val postService: PostService,
    private val commentService: CommentService,
    private val boardQueryRepository: BoardQueryRepository,
) {
    fun getPostsByBoardType(params: PostListGetParams): PageDto<PostListGetResponse> {
        val postSearchParams = params.toPostSearchParams()
        val count = boardQueryRepository.countPostByParams(postSearchParams)
        val result = boardQueryRepository.getPostByParams(postSearchParams)
            .map { PostListGetResponse from it }
        return PageDto(count = count, result = result)
    }

    @Transactional
    fun createPost(request: PostCreateRequest) {
        postService.create(
            memberId = request.memberId,
            board = request.board,
            title = request.title,
            content = request.content,
            tags = request.tags,
        )
    }

    @Transactional(readOnly = true)
    fun getPost(postId: Long): PostDetailGetResponse {
        val postDetailGetResponse: PostDetailGetResponse
        postService.find(postId)
            .also { postDetailGetResponse = PostDetailGetResponse from it }
        commentService.findByPostId(postId)
            .map { CommentDetailGetResponse from it }
            .also { postDetailGetResponse.comments = it }
        return postDetailGetResponse
    }

    @Transactional
    fun updatePost(request: PostUpdateRequest) {
        postService.update(
            id = request.postId,
            title = request.title,
            content = request.content,
            tags = request.tags,
        )
    }

    @Transactional
    fun deletePost(postId: Long) {
        postService.delete(postId)
        commentService.deleteByPostId(postId)
    }

    @Transactional
    fun createComment(request: CommentCreateRequest) {
        commentService.create(
            memberId = request.memberId,
            postId = request.postId,
            content = request.content,
        )
    }

    fun updateComment(request: CommentUpdateRequest) {
        commentService.update(
            id = request.commentId,
            content = request.content,
        )
    }

    fun deleteComment(commentId: Long) {
        commentService.delete(commentId)
    }
}
