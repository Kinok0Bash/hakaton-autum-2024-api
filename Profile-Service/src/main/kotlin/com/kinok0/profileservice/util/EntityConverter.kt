package com.kinok0.profileservice.util

import com.kinok0.profileservice.dto.Profile
import com.kinok0.profileservice.entity.UserEntity

fun UserEntity.convertToProfileDTO() = Profile(
    id = this.id!!,
    name = this.name,
    position = position,
    avatar = this.avatar
)