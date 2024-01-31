package com.leeheefull.domain.board.entity

import com.leeheefull.domain.common.BaseEntity
import java.time.LocalDateTime.now
import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.GenerationType
import javax.persistence.Id

@Entity
class Comment(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long? = -1L,

    val memberId: Long,

    val postId: Long,

    var content: String,

    var deleted: Boolean = false,
) : BaseEntity() {
    fun update(content: String?) {
        content?.let { this.content = it }
        this.updated = now()
    }

    fun delete() {
        this.deleted = true
        this.updated = now()
    }
}