package com.kinok0.profileservice.entity

import jakarta.persistence.*
import java.util.*

@Entity
@Table(name="comments")
data class CommentEntity(
    @Id
    @GeneratedValue(strategy= GenerationType.UUID)
    var id: UUID? = null,

    @Column(name = "comment", nullable = false)
    var comment: String,

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "\"user\"", referencedColumnName = "id")
    var user: UserEntity,

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "task", referencedColumnName = "id")
    var task: TaskEntity
)
