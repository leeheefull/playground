package com.leeheefull.domain.board.entity

import com.leeheefull.domain.common.BaseEntity
import com.leeheefull.domain.converter.StringListConverter
import com.leeheefull.domain.enums.BoardType
import java.time.LocalDateTime.now
import javax.persistence.Column
import javax.persistence.Convert
import javax.persistence.Entity
import javax.persistence.EnumType
import javax.persistence.Enumerated
import javax.persistence.GeneratedValue
import javax.persistence.GenerationType
import javax.persistence.Id

@Entity
class Post(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long? = -1L,

    @Column(nullable = false)
    val memberId: Long,

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    val board: BoardType,

    @Column(nullable = false)
    var title: String,

    @Column(nullable = false)
    var content: String,

    @Convert(converter = StringListConverter::class)
    @Column(nullable = true)
    var tags: List<String> = emptyList(),

    @Column(nullable = false)
    var viewCount: Long = 0L,

    @Column(nullable = false)
    var deleted: Boolean = false,
) : BaseEntity() {
    fun update(
        title: String?,
        content: String?,
        tags: List<String>?,
    ) {
        title?.let { this.title = it }
        content?.let { this.content = it }
        tags?.takeIf { it.isNotEmpty() }
            ?.let { this.tags = it }
        updated = now()
    }

    fun view() {
        this.viewCount++
    }

    fun addTag(tag: String) {
        val mutableTags = this.tags
            ?.toMutableList()
            ?: mutableListOf()
        mutableTags.add(tag)
        this.tags = mutableTags.toList()
        updated = now()
    }

    fun delete() {
        this.deleted = true
        this.updated = now()
    }
}