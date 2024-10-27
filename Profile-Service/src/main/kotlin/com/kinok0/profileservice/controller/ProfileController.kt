package com.kinok0.profileservice.controller

import com.kinok0.profileservice.dto.Profile
import com.kinok0.profileservice.dto.Task
import com.kinok0.profileservice.dto.request.CommentRequest
import com.kinok0.profileservice.service.CommentService
import com.kinok0.profileservice.service.JwtService
import com.kinok0.profileservice.service.ProfileService
import com.kinok0.profileservice.util.JwtException
import org.slf4j.LoggerFactory
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.*
import java.util.*

@Controller
@RequestMapping("/api/profile")
class ProfileController(
    private val profileService: ProfileService,
    private val jwtService: JwtService,
    private val commentService: CommentService
) {
    private val logger = LoggerFactory.getLogger(ProfileController::class.java)

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

    @GetMapping("/all")
    fun getFullProfileList(): ResponseEntity<MutableList<Profile>> {
        logger.info("Request to get full profile list")
        return ResponseEntity.ok(profileService.getFullProfileList())
    }

    @GetMapping("/")
    fun getProfile(@RequestHeader(name = "Authorization") token: String): ResponseEntity<Profile> {
        logger.info("Request to get profile user ${jwtService.extractUsername(token)}")
        return ResponseEntity.ok(profileService.getProfile(token))
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

    @PostMapping("/comment")
    fun createComment(
        @RequestHeader(name = "Authorization") token: String,
        @RequestBody request: CommentRequest
    ): ResponseEntity<Task> {
        logger.info("Request to creating comment from user ${jwtService.extractUsername(token)}")
        return ResponseEntity.ok(commentService.createComment(token, request))
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