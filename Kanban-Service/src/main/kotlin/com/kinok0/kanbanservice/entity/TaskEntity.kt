package com.kinok0.kanbanservice.entity

import jakarta.persistence.*
import java.time.LocalDateTime
import java.util.*

@Entity
@Table(name = "tasks")
data class TaskEntity(
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "id")
    var id: UUID? = null,

    @Column(name = "name", nullable = false)
    var name: String,

    @Column(name = "html_name", nullable = false)
    var htmlName: String,

    @Column(name = "description", nullable = false)
    var description: String,

    @Column(name = "priority", nullable = false)
    var priority: Int,

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "employee", referencedColumnName = "id")
    var employee: UserEntity? = null,

    @Column(name = "create_date", nullable = false)
    var createDate: LocalDateTime,

    @Enumerated(EnumType.STRING)
    @Column(name = "statement", nullable = false)
    var statement: TaskStatement,

    @OneToMany(mappedBy = "task", fetch = FetchType.LAZY)
    var comments: MutableList<CommentEntity>? = null
)
