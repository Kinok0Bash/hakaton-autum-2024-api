package com.kinok0.profileservice.repository

import com.kinok0.profileservice.entity.TaskEntity
import org.springframework.data.repository.CrudRepository
import org.springframework.stereotype.Repository
import java.util.UUID

@Repository
interface TaskRepository : CrudRepository<TaskEntity, UUID> {
    fun getTaskEntityById(id: UUID) : TaskEntity
}