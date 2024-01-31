package com.leeheefull.domain.board.service

import com.querydsl.jpa.impl.JPAQueryFactory
import org.springframework.stereotype.Repository

@Repository
class BoardQueryService(
    private val jpaQueryFactory: JPAQueryFactory,
) {
    // TODO 게시판 관련 조회 로직 구현
}