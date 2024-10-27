package com.kinok0.profileservice.util

import com.kinok0.profileservice.dto.Comment
import com.kinok0.profileservice.dto.Profile
import com.kinok0.profileservice.dto.Task
import com.kinok0.profileservice.dto.User
import com.kinok0.profileservice.entity.CommentEntity
import com.kinok0.profileservice.entity.TaskEntity
import com.kinok0.profileservice.entity.UserEntity

fun UserEntity.convertToProfileDTO() = Profile(
    id = this.id!!,
    name = this.name,
    position = position,
    avatar = this.avatar
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


fun CommentEntity.convertToCommentDTO() = Comment(
    text = this.comment,
    user = this.user.convertToUserDTO()
)