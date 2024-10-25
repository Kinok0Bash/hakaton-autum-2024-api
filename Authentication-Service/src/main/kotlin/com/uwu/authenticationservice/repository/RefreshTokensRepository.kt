package com.uwu.authenticationservice.repository

import com.uwu.authenticationservice.entity.RefreshTokensEntity
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository
import java.util.UUID

@Repository
interface RefreshTokensRepository : JpaRepository<RefreshTokensEntity, UUID> {
    fun findRefreshTokensEntityByToken(token: String): RefreshTokensEntity
    override fun findAll(): MutableList<RefreshTokensEntity>
}