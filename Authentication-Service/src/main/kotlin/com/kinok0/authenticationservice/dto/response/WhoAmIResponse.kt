package com.kinok0.authenticationservice.dto.response

import com.kinok0.migrationservice.entity.Role
import java.util.*

data class WhoAmIResponse(
    var id: UUID,
    var name: String,
    var role: Role
)