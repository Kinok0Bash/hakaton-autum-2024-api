package com.kinok0.authenticationservice.service

import com.kinok0.authenticationservice.repository.UserRepository
import com.kinok0.authenticationservice.dto.response.WhoAmIResponse
import com.kinok0.authenticationservice.util.convertToWhoAmIResponse
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.stereotype.Service

@Service
class WhoAmIService(
    private val userRepository: UserRepository,
    private val jwtService: JwtService
) {
    private val logger: Logger = LoggerFactory.getLogger(WhoAmIService::class.java)

    fun whoAmI(token: String): WhoAmIResponse {
        val user = userRepository.findByLogin(jwtService.extractUsername(token.substring(7)))
        logger.info("WhoAmI for user ${user.login} has been returned")
        return user.convertToWhoAmIResponse()
    }
}