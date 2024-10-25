package com.uwu.authenticationservice.dto.request

data class RegistrationRequest(
    var email: String,
    var password: String,
    var name: String,
    var lastname: String
)
