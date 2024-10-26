package com.kinok0.kanbanservice.dto.request

import java.util.UUID

data class ChangePriorityRequest(
    var id: UUID,
    var priority: Int
)
