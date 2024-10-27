package com.kinok0.profileservice.entity

import jakarta.persistence.*
import java.util.*

@Entity
@Table(name = "refresh_tokens")
data class RefreshTokenEntity(
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    var id: UUID? = null,

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "\"user\"", referencedColumnName = "id", nullable = false)
    var user: UserEntity,

    @Column(name = "token", nullable = false, unique = true)
    var token: String
)
