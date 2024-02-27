package com.leeheefull.api.board.controller.request

import com.fasterxml.jackson.annotation.JsonIgnore
import com.leeheefull.domain.board.query.param.PostSearchParams
import com.leeheefull.domain.enums.BoardSortColumn
import com.leeheefull.domain.enums.BoardSortColumn.CREATED
import com.leeheefull.domain.enums.BoardType
import com.leeheefull.domain.enums.BoardType.NOTICE
import java.time.LocalDate
import org.springframework.data.domain.PageRequest
import org.springframework.format.annotation.DateTimeFormat

data class PostListGetParams(
    @JsonIgnore
    var boardType: BoardType = NOTICE,

    val searchTitle: String? = null,

    val tag: String? = null,

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    val createStartDate: LocalDate? = null,

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    val createEndDate: LocalDate? = null,

    val pageNumber: Int = 0,

    val pageSize: Int = 10,

    val sortColumn: BoardSortColumn = CREATED,

    val isDescending: Boolean = true,
) {
    fun toPostSearchParams(): PostSearchParams =
        PostSearchParams(
            boardType = boardType,
            searchTitle = searchTitle,
            tag = tag,
            createStartDate = createStartDate,
            createEndDate = createEndDate,
            pageable = PageRequest.of(pageNumber, pageSize),
            sortColumn = sortColumn,
            isDescending = isDescending,
        )
}
