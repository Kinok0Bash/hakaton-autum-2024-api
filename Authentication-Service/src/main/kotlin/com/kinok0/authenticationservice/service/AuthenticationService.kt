package com.kinok0.authenticationservice.service

import com.kinok0.authenticationservice.dto.request.AuthenticationRequest
import com.kinok0.authenticationservice.dto.request.RegistrationRequest
import com.kinok0.authenticationservice.dto.response.AuthenticationResponse
import com.kinok0.authenticationservice.entity.RefreshTokenEntity
import com.kinok0.authenticationservice.entity.Role
import com.kinok0.authenticationservice.entity.UserEntity
import com.kinok0.authenticationservice.repository.RefreshTokensRepository
import com.kinok0.authenticationservice.repository.UserRepository
import com.kinok0.authenticationservice.util.convertToMemberData
import com.kinok0.authenticationservice.util.convertToUserDTO
import jakarta.servlet.http.Cookie
import jakarta.servlet.http.HttpServletResponse
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.http.HttpHeaders
import org.springframework.http.ResponseCookie
import org.springframework.security.authentication.AuthenticationManager
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import java.time.Duration

@Service
class AuthenticationService(
    private val userRepository: UserRepository,
    private val jwtService: JwtService,
    private val authenticationManager: AuthenticationManager,
    private val passwordEncoder: PasswordEncoder,
    private val refreshTokensRepository: RefreshTokensRepository
) {
    private val logger: Logger = LoggerFactory.getLogger(AuthenticationService::class.java)

    @Transactional
    fun authorization(request: AuthenticationRequest, response: HttpServletResponse): AuthenticationResponse {
        if (!isValidAuthenticationCredentials(request)) {
            logger.error("Login and/or password is empty")
            throw Exception("Поля логин и/или пароль пустые")
        }
        authenticationManager.authenticate(UsernamePasswordAuthenticationToken(request.login, request.password))
        val user = userRepository.findByLogin(request.login)
        logger.debug("User ${user.login} is authorized")
        logger.info("Authorization is successful!")

        val userDetails = user.convertToUserDTO()

        val tokens = jwtService.generateTokens(userDetails)

        val tokensEntity = RefreshTokenEntity (
            user = user,
            token = tokens[1]
        )
        refreshTokensRepository.save(tokensEntity)

        setRefreshToken(response, tokens[1])

        return AuthenticationResponse(tokens[0], user.convertToMemberData())
    }

    @Transactional
    fun registration(request: RegistrationRequest, response: HttpServletResponse): AuthenticationResponse {
        if (!isValidRegistrationCredentials(request)) {
            logger.error("Data is empty")
            throw Exception("Заполнены не все данные!!!")
        }

        val emails = userRepository.findAllLogins()

        emails.forEach { email ->
            if (request.login == email) {
                logger.error("Error of registration: User with email ${request.login} is already exist")
                throw Exception("Пользователь с таким email уже существует")
            }
        }

        val user = UserEntity (
            login = request.login,
            password = passwordEncoder.encode(request.password),
            name = request.fio,
            role = Role.GUEST
        )

        val userDetails = user.convertToUserDTO()

        val tokens = jwtService.generateTokens(userDetails)

        userRepository.save(user)
        setRefreshToken(response, tokens[1])

        val tokensEntity = RefreshTokenEntity(
            user = user,
            token = tokens[1]
        )
        refreshTokensRepository.save(tokensEntity)

        logger.debug("User with email ${user.login} has been created")
        logger.info("Registration is successful!")

        return AuthenticationResponse(tokens[0], user.convertToMemberData())
    }

    fun logout(token: String, response: HttpServletResponse): Map<String, String> {
        val tokensEntity = refreshTokensRepository.findRefreshTokensEntityByToken(token)
        refreshTokensRepository.delete(tokensEntity)

        val cookie = Cookie("refreshToken", null)
        cookie.maxAge = 0
        cookie.path = "/"
        response.addCookie(cookie)

        return mapOf("message" to "Logout successful")
    }

    @Transactional
    fun refresh(userToken: String, response: HttpServletResponse): AuthenticationResponse {
        if (userToken.isEmpty()) {
            logger.error("Token is empty")
            throw Exception("Token is empty")
        }

        val user = userRepository.findByLogin(jwtService.extractUsername(userToken))

        val refreshTokens: MutableList<RefreshTokenEntity> = refreshTokensRepository.findAll()
        for (token in refreshTokens) {
            if (token.token == userToken) {
                logger.error("Token not valid")
                throw Exception("Token not valid")
            }
        }

        val userDetails = user.convertToUserDTO()

        val tokens = jwtService.generateTokens(userDetails)

        refreshTokensRepository.save(
            RefreshTokenEntity(
            user = user,
            token = tokens[1]
        )
        )

        userRepository.save(user)
        setRefreshToken(response, tokens[1])

        logger.debug("Token of user ${user.login} is refreshed")

        return AuthenticationResponse(tokens[0], user.convertToMemberData())
    }

    fun setRefreshToken(response: HttpServletResponse, token: String) {
        val cookie = ResponseCookie.from("refreshToken", token)
            .httpOnly(true)
            .secure(true)
            .path("/")
            .maxAge(Duration.ofDays(30))
            .sameSite("None")
            .build()

        response.addHeader(HttpHeaders.SET_COOKIE, cookie.toString())
    }

    private fun isValidAuthenticationCredentials(request: AuthenticationRequest) =
        request.login.isNotEmpty() && request.password.isNotEmpty()

    private fun isValidRegistrationCredentials(request: RegistrationRequest) =
        request.login.isNotEmpty() && request.password.isNotEmpty() && request.fio.isNotEmpty()

}