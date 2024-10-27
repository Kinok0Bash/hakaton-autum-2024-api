package com.kinok0.kanbanservice.repository

import com.kinok0.kanbanservice.entity.TaskEntity
import com.kinok0.kanbanservice.entity.TaskStatement
import org.springframework.data.repository.CrudRepository
import org.springframework.stereotype.Repository
import java.util.*

@Repository
interface TaskRepository : CrudRepository<TaskEntity, UUID> {

    fun findTaskEntityById(id: UUID) : TaskEntity

    fun findTaskEntitiesByStatement(statement: TaskStatement) : MutableList<TaskEntity>

    fun deleteTaskEntityById(id: UUID)

}