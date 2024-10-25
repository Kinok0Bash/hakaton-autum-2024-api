package com.kinok0.migrationservice.entity

import jakarta.persistence.*
import java.util.*

@Entity
@Table(name = "users")
data class UserEntity(
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "id")
    var id: UUID? = null,

    @Column(name = "name", nullable = false)
    var name: String,

    @Column(name = "login", nullable = false)
    var login: String,

    @Column(name = "password", nullable = false)
    var password: String,

    @Column(name = "role", nullable = false)
    var role: String,

    @Column(name = "position", nullable = false)
    var position: String,

    @Column(name = "avatar", nullable = false)
    var avatar: ByteArray,

    @OneToMany(mappedBy = "employee")
    var tasks: MutableList<TaskEntity>,

    @OneToMany(mappedBy = "user")
    var comments: MutableList<CommentEntity>,

    @OneToMany(mappedBy = "user")
    var tokens: MutableList<RefreshTokenEntity>
)