package com.uwu.authenticationservice.dto

import com.uwu.authenticationservice.entity.Role

data class MemberData(
    val email: String,
    val isActivated: Boolean,
    val role: Role
)