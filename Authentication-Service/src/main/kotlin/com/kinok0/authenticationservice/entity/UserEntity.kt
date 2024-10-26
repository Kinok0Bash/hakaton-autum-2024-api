package com.kinok0.authenticationservice.entity

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
    var role: Role,

    @Column(name = "position")
    var position: String? = null,

    @Column(name = "avatar")
    var avatar: ByteArray? = null,

    @OneToMany(mappedBy = "employee")
    var tasks: MutableList<TaskEntity>? = null,

    @OneToMany(mappedBy = "user")
    var comments: MutableList<CommentEntity>? = null,

    @OneToMany(mappedBy = "user")
    var tokens: MutableList<RefreshTokenEntity>? = null
)