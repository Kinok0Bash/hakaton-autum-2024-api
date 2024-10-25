package com.kinok0.authenticationservice.dto.response

import com.kinok0.authenticationservice.dto.MemberData

data class AuthenticationResponse(
    var token: String,
    var user: MemberData
)
