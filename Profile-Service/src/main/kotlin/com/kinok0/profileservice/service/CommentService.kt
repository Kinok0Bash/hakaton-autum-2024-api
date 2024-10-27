package com.kinok0.profileservice.service

import com.kinok0.profileservice.dto.Task
import com.kinok0.profileservice.dto.request.CommentRequest
import com.kinok0.profileservice.entity.CommentEntity
import com.kinok0.profileservice.entity.TaskEntity
import com.kinok0.profileservice.repository.CommentRepository
import com.kinok0.profileservice.repository.TaskRepository
import com.kinok0.profileservice.repository.UserRepository
import com.kinok0.profileservice.util.convertToTaskDTO
import org.springframework.stereotype.Service

@Service
class CommentService(
    private val userRepository: UserRepository,
    private val taskRepository: TaskRepository,
    private val jwtService: JwtService,
    private val commentRepository: CommentRepository
) {

    fun createComment(token: String, request: CommentRequest): Task {
        val comment = CommentEntity(
            comment = request.text,
            user = userRepository.getUserEntityByLogin(jwtService.extractUsername(token)),
            task = taskRepository.getTaskEntityById(request.taskId)
        )
        commentRepository.save(comment)
        return taskRepository.getTaskEntityById(request.taskId).convertToTaskDTO()
    }

}