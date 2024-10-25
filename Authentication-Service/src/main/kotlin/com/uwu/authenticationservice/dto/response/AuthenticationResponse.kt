package com.uwu.authenticationservice.dto.response

import com.uwu.authenticationservice.dto.MemberData

data class AuthenticationResponse(
    var token: String,
    var user: MemberData
)
