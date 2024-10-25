package com.kinok0.authenticationservice.repository

import com.kinok0.migrationservice.entity.UserEntity
import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.CrudRepository
import org.springframework.stereotype.Repository
import java.util.*

@Repository
interface UserRepository : CrudRepository<UserEntity, UUID> {
    fun findByLogin(login: String): UserEntity

    @Query("select u.login from UserEntity u")
    fun findAllLogins() : List<String>
}
