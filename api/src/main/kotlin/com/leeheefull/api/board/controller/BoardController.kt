package com.leeheefull.api.board.controller

import com.leeheefull.api.board.controller.request.CommentCreateRequest
import com.leeheefull.api.board.controller.request.CommentUpdateRequest
import com.leeheefull.api.board.controller.request.PostCreateRequest
import com.leeheefull.api.board.controller.request.PostListGetParams
import com.leeheefull.api.board.controller.request.PostUpdateRequest
import com.leeheefull.api.board.facade.BoardFacade
import com.leeheefull.api.board.facade.response.PostDetailGetResponse
import com.leeheefull.api.board.facade.response.PostListGetResponse
import com.leeheefull.api.common.dto.PageDto
import com.leeheefull.domain.enums.BoardType
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PatchMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.PutMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping(value = ["/v1/boards"])
class BoardController(
    private val boardFacade: BoardFacade,
) {
    @GetMapping(value = ["/{boardType}/posts"])
    fun getPostsByBoardType(
        @PathVariable boardType: BoardType,
        params: PostListGetParams,
    ): PageDto<PostListGetResponse> {
        params.boardType = boardType
        return boardFacade.getPostsByBoardType(params)
    }

    @PostMapping(value = ["/posts"])
    fun createPost(
        @RequestBody request: PostCreateRequest,
    ) {
        boardFacade.createPost(request)
    }

    @GetMapping(value = ["/posts/{postId}"])
    fun getPost(@PathVariable postId: Long): PostDetailGetResponse =
        boardFacade.getPost(postId)

    @PutMapping(value = ["/posts/{postId}"])
    fun updatePost(
        @PathVariable postId: Long,
        @RequestBody request: PostUpdateRequest,
    ) {
        request.postId = postId
        boardFacade.updatePost(request)
    }

    @PatchMapping(value = ["/posts/{postId}"])
    fun deletePost(@PathVariable postId: Long) {
        boardFacade.deletePost(postId)
    }

    @PostMapping(value = ["/posts/{postId}/comments"])
    fun createComment(
        @PathVariable postId: Long,
        @RequestBody request: CommentCreateRequest,
    ) {
        request.postId = postId
        boardFacade.createComment(request)
    }

    @PutMapping(value = ["/posts/comments/{commentId}"])
    fun updateComment(
        @PathVariable commentId: Long,
        @RequestBody request: CommentUpdateRequest,
    ) {
        request.commentId = commentId
        boardFacade.updateComment(request)
    }

    @PatchMapping(value = ["/posts/comments/{commentId}"])
    fun updateComment(
        @PathVariable commentId: Long,
    ) {
        boardFacade.deleteComment(commentId)
    }
}