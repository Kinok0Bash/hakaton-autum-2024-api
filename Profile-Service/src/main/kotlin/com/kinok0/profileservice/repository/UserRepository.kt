package com.kinok0.profileservice.repository

import com.kinok0.profileservice.entity.UserEntity
import org.springframework.data.repository.CrudRepository
import org.springframework.stereotype.Repository
import java.util.UUID

@Repository
interface UserRepository : CrudRepository<UserEntity, UUID>{
    fun getUserEntityById(id: UUID) : UserEntity
    fun getUserEntityByLogin(login: String) : UserEntity
}