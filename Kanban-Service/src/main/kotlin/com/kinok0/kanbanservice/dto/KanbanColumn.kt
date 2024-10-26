package com.kinok0.kanbanservice.dto

import com.kinok0.kanbanservice.entity.TaskEntity

data class KanbanColumn(
    var columnName: String,
    var tasks: MutableList<TaskEntity>
)
