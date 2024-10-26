package com.kinok0.kanbanservice.dto.request

import com.kinok0.kanbanservice.entity.TaskStatement
import java.util.UUID

data class ChangeStatementRequest(
    var id: UUID,
    var statement: TaskStatement
)
