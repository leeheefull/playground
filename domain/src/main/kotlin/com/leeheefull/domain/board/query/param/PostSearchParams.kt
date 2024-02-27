package com.leeheefull.domain.board.query.param

import com.leeheefull.domain.enums.BoardSortColumn
import com.leeheefull.domain.enums.BoardSortColumn.CREATED
import com.leeheefull.domain.enums.BoardType
import java.time.LocalDate
import org.springframework.data.domain.PageRequest
import org.springframework.data.domain.Pageable

data class PostSearchParams(
    var boardType: BoardType? = null,
    val searchTitle: String? = null,
    val tag: String? = null,
    val createStartDate: LocalDate? = null,
    val createEndDate: LocalDate? = null,
    val pageable: Pageable = PageRequest.of(0, 10),
    val sortColumn: BoardSortColumn = CREATED,
    val isDescending: Boolean = true,
)
