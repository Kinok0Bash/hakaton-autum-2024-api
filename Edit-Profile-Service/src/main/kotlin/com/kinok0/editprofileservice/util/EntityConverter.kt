package com.kinok0.editprofileservice.util

import com.kinok0.editprofileservice.dto.response.UserResponse
import com.kinok0.editprofileservice.entity.UserEntity


fun UserEntity.convertToUserDTO(): UserResponse {
    val result: UserResponse = UserResponse(
        name = this.name,
        position = this.position,
        avatar = this.avatar,
    )
    return result
}
