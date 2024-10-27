package com.kinok0.profileservice.dto

import java.util.UUID

data class Profile(
    var id: UUID,
    var name: String,
    var position: String?,
    var avatar: ByteArray? = null
)
