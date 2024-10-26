package com.kinok0.kanbanservice.dto.request

import java.util.UUID

data class ChangeNameRequest(
    var id: UUID,
    var name: String,
    var htmlName: String
)
