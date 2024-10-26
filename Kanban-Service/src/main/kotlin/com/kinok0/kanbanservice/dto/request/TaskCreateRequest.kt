package com.kinok0.kanbanservice.dto.request

data class TaskCreateRequest(
    var name: String,
    var htmlName: String,
    var description: String
)
