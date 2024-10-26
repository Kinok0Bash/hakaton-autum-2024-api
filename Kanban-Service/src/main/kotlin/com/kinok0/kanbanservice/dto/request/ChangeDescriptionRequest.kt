package com.kinok0.kanbanservice.dto.request

import java.util.UUID

data class ChangeDescriptionRequest(
    var id: UUID,
    var description: String
)
