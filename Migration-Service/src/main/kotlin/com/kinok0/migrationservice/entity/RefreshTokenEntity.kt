package com.kinok0.migrationservice.entity

import jakarta.persistence.*
import java.util.*

@Entity
@Table(name = "refresh_tokens")
data class RefreshTokenEntity(
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    var id: UUID? = null,

    @ManyToOne
    @JoinColumn(name = "user", referencedColumnName = "id", nullable = false, unique = false)
    var user: UserEntity,

    @Column(name = "token", nullable = false, unique = true)
    var token: String
)
