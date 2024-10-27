package com.kinok0.kanbanservice.repository

import com.kinok0.kanbanservice.entity.UserEntity
import org.springframework.data.repository.CrudRepository
import java.util.UUID

interface UserRepository : CrudRepository<UserEntity, UUID> {
    fun getUserEntityById(id: UUID) : UserEntity

    fun getUserEntityByLogin(login: String): UserEntity
}