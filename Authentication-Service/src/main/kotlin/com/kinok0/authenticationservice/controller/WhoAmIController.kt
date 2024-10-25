package com.kinok0.authenticationservice.controller

import com.kinok0.authenticationservice.dto.response.WhoAmIResponse
import com.kinok0.authenticationservice.service.WhoAmIService
import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.tags.Tag
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/api/authentication/whoami")
@Tag(
    name = "WhoAmI",
    description = "Полная инфа об аутентифицированном пользователе"
)
class WhoAmIController(private val whoAmIService: WhoAmIService) {
    private val logger: Logger = LoggerFactory.getLogger(WhoAmIController::class.java)

    @GetMapping
    @Operation(description = "Полная инфа об аутентифицированном пользователе. Поверь, позже пригодится!!!")
    fun whoAmI(@RequestHeader(value = "Authorization") token: String): ResponseEntity<WhoAmIResponse> {
        logger.info("Request to WhoAmI")
        return ResponseEntity.ok(whoAmIService.whoAmI(token))
    }

    @ExceptionHandler
    fun handleException(ex: Exception) : ResponseEntity<*> {
        logger.error("Exception: $ex")
        return ResponseEntity.badRequest().body(mapOf("error" to "${ex.message}"))
    }

}