package com.kinok0.kanbanservice.util

import com.kinok0.kanbanservice.dto.*
import com.kinok0.kanbanservice.entity.CommentEntity
import com.kinok0.kanbanservice.entity.TaskEntity
import com.kinok0.kanbanservice.entity.UserEntity

fun TaskEntity.convertToTaskCard() = TaskCard(
    id = this.id!!,
    name = this.name,
    employee = this.employee?.name,
)

fun TaskEntity.convertToTaskDTO(): Task {
    val result = Task(
        name = this.name,
        descryption = this.description,
        employee = this.employee?.convertToUserDTO(),
        createDate = this.createDate,
        comments = ArrayList()
    )
    this.comments!!.forEach { entity ->
        result.comments.add(entity.convertToCommentDTO())
    }
    return result
}

fun UserEntity.convertToUserDTO() = User(
    id = this.id!!,
    name = this.name
)

//fun UserEntity.convertToUserDetailsDTO() = UserDetailsDTO(
//    login = this.login,
//    authPassword = this.password,
//    role = this.role
//)

fun CommentEntity.convertToCommentDTO() = Comment(
    text = this.comment,
    user = this.user.convertToUserDTO()
)