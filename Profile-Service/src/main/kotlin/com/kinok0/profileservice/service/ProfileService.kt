package com.kinok0.profileservice.service

import com.kinok0.profileservice.dto.Profile
import com.kinok0.profileservice.repository.UserRepository
import com.kinok0.profileservice.util.JwtException
import com.kinok0.profileservice.util.convertToProfileDTO
import org.springframework.stereotype.Service
import java.util.*
@Service
class ProfileService(
    private val userRepository: UserRepository,
    private val jwtService: JwtService
) {

    fun getProfile(token: String): Profile {
        if (!jwtService.isTokenValid(token)) throw JwtException()
        return userRepository.getUserEntityByLogin(jwtService.extractUsername(token)).convertToProfileDTO()
    }

    fun getProfile(id: UUID) = userRepository.getUserEntityById(id).convertToProfileDTO()

    fun changeName(token: String, name: String): Profile {
        if (!jwtService.isTokenValid(token)) throw JwtException()
        val entity = userRepository.getUserEntityByLogin(jwtService.extractUsername(token))
        entity.name = name
        return userRepository.save(entity).convertToProfileDTO()
    }

    fun changePosition(token: String, position: String): Profile {
        if (!jwtService.isTokenValid(token)) throw JwtException()
        val entity = userRepository.getUserEntityByLogin(jwtService.extractUsername(token))
        entity.position = position
        return userRepository.save(entity).convertToProfileDTO()
    }

    fun changeAvatar(token: String, avatar: ByteArray): Profile {
        if (!jwtService.isTokenValid(token)) throw JwtException()
        val entity = userRepository.getUserEntityByLogin(jwtService.extractUsername(token))
        entity.avatar = avatar
        return userRepository.save(entity).convertToProfileDTO()
    }

}

