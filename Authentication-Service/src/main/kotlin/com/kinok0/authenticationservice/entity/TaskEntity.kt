package com.kinok0.authenticationservice.entity

import jakarta.persistence.*
import java.time.LocalDateTime
import java.util.*

@Entity
@Table(name = "tasks")
data class TaskEntity (
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "id")
    var id: UUID? = null,

    @Column(name = "name", nullable = false)
    var name: String,

    @Column(name = "description", nullable = false)
    var description: String,

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "employee", referencedColumnName = "id", nullable = false)
    var employee: UserEntity,

    @Column(name="create_date", nullable = false)
    var createDate: LocalDateTime,

    @Column(name = "statement", nullable = false)
    var statement: TaskStatement,

    @OneToMany(mappedBy = "task", fetch = FetchType.LAZY)
    var comments: MutableList<CommentEntity>? = null
)

