package com.uwu.authenticationservice.util

import com.uwu.authenticationservice.dto.MemberData
import com.uwu.authenticationservice.dto.User
import com.uwu.authenticationservice.entity.UserEntity

fun UserEntity.convertToMemberData() = MemberData(
    email = this.email,
    isActivated = this.isActivated,
    role = this.role
)

fun UserEntity.convertToUserDTO() = User(
    email = this.email,
    authPassword = this.password,
    role = this.role
)