package com.leeheefull.domain.board.query

import com.leeheefull.domain.board.entity.QPost.post
import com.leeheefull.domain.board.query.param.PostSearchParams
import com.leeheefull.domain.board.query.result.PostSearchResult
import com.leeheefull.domain.enums.BoardSortColumn
import com.leeheefull.domain.enums.BoardSortColumn.CREATED
import com.leeheefull.domain.enums.BoardSortColumn.UPDATED
import com.querydsl.core.BooleanBuilder
import com.querydsl.core.types.OrderSpecifier
import com.querydsl.core.types.Projections
import com.querydsl.core.types.dsl.Expressions
import com.querydsl.jpa.impl.JPAQueryFactory
import java.time.LocalDateTime
import java.time.LocalTime.MAX
import java.time.LocalTime.MIDNIGHT
import org.springframework.stereotype.Repository


@Repository
class BoardQueryRepository(
    private val jpaQueryFactory: JPAQueryFactory,
) {
    fun countPostByParams(params: PostSearchParams): Long {
        return jpaQueryFactory
            .select(post.count())
            .from(post)
            .where(getPostSearchBooleanBuilder(params))
            .fetchOne() ?: 0L
    }

    fun getPostByParams(params: PostSearchParams): List<PostSearchResult> {
        return jpaQueryFactory
            .select(
                Projections.constructor(
                    PostSearchResult::class.java,
                    post.id,
                    post.memberId,
                    post.board,
                    post.title,
                    post.content,
                    post.tags,
                    post.viewCount
                )
            )
            .from(post)
            .where(getPostSearchBooleanBuilder(params))
            .limit(params.pageable.pageSize.toLong())
            .offset(params.pageable.offset)
            .orderBy(getPostSearchOrderSpecifier(params))
            .fetch()
    }

    private fun getPostSearchBooleanBuilder(params: PostSearchParams): BooleanBuilder {
        val builder = BooleanBuilder()
        if (params.boardType != null) {
            builder.and(
                post.board.eq(params.boardType)
            )
        }
        if (params.searchTitle != null) {
            builder.and(
                post.title.contains(params.searchTitle)
            )
        }
        if (params.tag != null) {
            builder.and(
                Expressions.stringTemplate("JSON_EXTRACT({0}, '$[*]')", post.tags)
                    .contains(params.tag)
            )
        }
        if (params.createStartDate != null && params.createEndDate != null) {
            builder.and(
                post.created.goe(LocalDateTime.of(params.createStartDate, MIDNIGHT))
                    .and(post.created.loe(LocalDateTime.of(params.createEndDate, MAX)))
            )
        }
        return builder
    }

    private fun getPostSearchOrderSpecifier(params: PostSearchParams): OrderSpecifier<out Comparable<*>> {
        val orderSpecifier =
            when (params.sortColumn) {
                CREATED -> post.created
                UPDATED -> post.created
            }
        return if (params.isDescending) orderSpecifier.desc() else orderSpecifier.asc()
    }
}