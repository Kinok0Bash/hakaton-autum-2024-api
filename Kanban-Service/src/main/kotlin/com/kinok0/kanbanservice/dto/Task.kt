package com.kinok0.kanbanservice.dto

import java.time.LocalDateTime

data class Task(
    var name: String,
    var descryption: String,
    var employee: User?,
    var createDate: LocalDateTime,
    var comments: MutableList<Comment>
)