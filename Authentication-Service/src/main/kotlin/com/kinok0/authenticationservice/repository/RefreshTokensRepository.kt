package com.kinok0.authenticationservice.repository

import com.kinok0.authenticationservice.entity.RefreshTokenEntity
import org.springframework.data.repository.CrudRepository
import org.springframework.stereotype.Repository
import java.util.*

@Repository
interface RefreshTokensRepository : CrudRepository<RefreshTokenEntity, UUID> {
    fun findRefreshTokensEntityByToken(token: String): RefreshTokenEntity
    override fun findAll(): MutableList<RefreshTokenEntity>
}