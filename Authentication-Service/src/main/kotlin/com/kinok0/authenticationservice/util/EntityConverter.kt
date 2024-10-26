package com.kinok0.authenticationservice.util

import com.kinok0.authenticationservice.dto.MemberData
import com.kinok0.authenticationservice.dto.User
import com.kinok0.authenticationservice.dto.response.WhoAmIResponse
import com.kinok0.authenticationservice.entity.UserEntity

fun UserEntity.convertToMemberData() = MemberData(
    login = this.login,
    role = this.role
)

fun UserEntity.convertToUserDTO() = User(
    login = this.login,
    authPassword = this.password,
    role = this.role
)

fun UserEntity.convertToWhoAmIResponse() = WhoAmIResponse(
    id = this.id!!,
    name = this.name,
    role = this.role
)