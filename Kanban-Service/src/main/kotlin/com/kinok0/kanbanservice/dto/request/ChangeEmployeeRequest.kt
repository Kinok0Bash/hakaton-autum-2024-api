package com.kinok0.kanbanservice.dto.request

import java.util.UUID

data class ChangeEmployeeRequest(
    var id: UUID,
    var employee: UUID
)
