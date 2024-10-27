package com.kinok0.profileservice.controller

import com.kinok0.profileservice.dto.Profile
import com.kinok0.profileservice.service.JwtService
import com.kinok0.profileservice.service.ProfileService
import com.kinok0.profileservice.util.JwtException
import org.slf4j.LoggerFactory
import org.springframework.http.HttpStatus
import org.springframework.http.HttpStatusCode
import org.springframework.http.ResponseEntity
import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PutMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestHeader
import org.springframework.web.bind.annotation.RequestMapping
import java.util.*

@Controller
@RequestMapping("/api/profile")
class ProfileController(
    private val profileService: ProfileService,
    private val jwtService: JwtService
) {
    private val logger = LoggerFactory.getLogger(ProfileController::class.java)

    @GetMapping("/")
    fun getProfile(@RequestHeader(name = "Authorization") token: String): ResponseEntity<Profile> {
        logger.info("Request to get profile user ${jwtService.extractUsername(token)}")
        return ResponseEntity.ok(profileService.getProfile(token))
    }

    @GetMapping("/{id}")
    fun getProfile(@PathVariable id: UUID): ResponseEntity<Profile> {
        logger.info("Request to get profile by UUID $id")
        return ResponseEntity.ok(profileService.getProfile(id))
    }

    @PutMapping("/edit/name")
    fun changeName(
        @RequestHeader(name = "Authorization") token: String,
        @RequestBody request: Map<String, String>
    ): ResponseEntity<Profile> {
        logger.info("Request to change name profile user ${jwtService.extractUsername(token)}")
        return ResponseEntity.ok(profileService.changeName(token, request["name"]!!))
    }

    @PutMapping("/edit/position")
    fun changePosition(
        @RequestHeader(name = "Authorization") token: String,
        @RequestBody request: Map<String, String>
    ): ResponseEntity<Profile> {
        logger.info("Request to change position profile user ${jwtService.extractUsername(token)}")
        return ResponseEntity.ok(profileService.changePosition(token, request["position"]!!))
    }

    @PutMapping("/edit/avatar")
    fun changeAvatar(
        @RequestHeader(name = "Authorization") token: String,
        @RequestBody request: Map<String, ByteArray>
    ): ResponseEntity<Profile> {
        logger.info("Request to change avatar profile user ${jwtService.extractUsername(token)}")
        return ResponseEntity.ok(profileService.changeAvatar(token, request["avatar"]!!))
    }

    @ExceptionHandler
    fun handleJwtException(ex: JwtException) : ResponseEntity<*> {
        logger.error("JwtException: $ex")
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(mapOf("error" to ex.message))
    }

    @ExceptionHandler
    fun handleException(ex: Exception) : ResponseEntity<*> {
        logger.error("Exception: $ex")
        return ResponseEntity.badRequest().body(mapOf("error" to "${ex.message}"))
    }

}