package com.kinok0.authenticationservice.dto.request

data class RegistrationRequest(
    var fio: String,
    var login: String,
    var password: String
)
