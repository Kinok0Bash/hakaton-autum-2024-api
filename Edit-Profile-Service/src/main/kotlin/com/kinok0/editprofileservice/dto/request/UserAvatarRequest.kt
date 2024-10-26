package com.kinok0.editprofileservice.dto.request

import java.util.*

data class UserAvatarRequest(
    var id: UUID,
    var avatar: ByteArray
)
