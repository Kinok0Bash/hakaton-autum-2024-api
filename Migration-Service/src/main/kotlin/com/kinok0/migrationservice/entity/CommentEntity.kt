package com.kinok0.migrationservice.entity

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

    @ManyToOne()
    @JoinColumn(name = "user", referencedColumnName = "id")
    var user: UserEntity,

    @ManyToOne
    @JoinColumn(name = "task", referencedColumnName = "id")
    var task: TaskEntity
)


