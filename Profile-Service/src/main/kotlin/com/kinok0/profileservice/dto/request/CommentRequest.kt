package com.kinok0.profileservice.dto.request

import java.util.*

data class CommentRequest(
    var taskId: UUID,
    var text: String
)
