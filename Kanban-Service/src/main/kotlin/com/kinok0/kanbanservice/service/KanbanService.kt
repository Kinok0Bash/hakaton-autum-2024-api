package com.kinok0.kanbanservice.service

import com.kinok0.kanbanservice.dto.KanbanColumn
import com.kinok0.kanbanservice.dto.Task
import com.kinok0.kanbanservice.dto.request.*
import com.kinok0.kanbanservice.entity.TaskEntity
import com.kinok0.kanbanservice.entity.TaskStatement
import com.kinok0.kanbanservice.repository.TaskRepository
import com.kinok0.kanbanservice.repository.UserRepository
import com.kinok0.kanbanservice.util.convertToTaskDTO
import org.slf4j.LoggerFactory
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import java.time.LocalDateTime
import java.util.*

@Service
class KanbanService(
    private val taskRepository: TaskRepository,
    private val userRepository: UserRepository
) {
    private val logger = LoggerFactory.getLogger(KanbanService::class.java)

    fun getKanban(): ArrayList<KanbanColumn> {
        val kanban = ArrayList<KanbanColumn>()

        for (statement in TaskStatement.entries) {
            val name = when (statement) {
                TaskStatement.BACKLOG -> "Новые"
                TaskStatement.INBOX -> "В процессе"
                TaskStatement.COMPLETED -> "Завершенные"
            }
            val column = KanbanColumn(
                columnName = name,
                tasks = taskRepository.findTaskEntitiesByStatement(statement)
            )
            kanban.add(column)
        }

        return kanban
    }

    fun getTaskById(id: UUID): Task {
        val taskEntity = taskRepository.findTaskEntityById(id)
        return taskEntity.convertToTaskDTO()
    }

    fun createTask(request: TaskCreateRequest) : ArrayList<KanbanColumn> {
        taskRepository.save(
            TaskEntity(
                name = request.name,
                description = request.name,
                createDate = LocalDateTime.now(),
                statement = TaskStatement.BACKLOG,
                priority = 0
            )
        )
        logger.info("Entity has been created")
        return getKanban()
    }

    fun changeStatement(request: ChangeStatementRequest): Task {
        val entity = taskRepository.findTaskEntityById(request.id)
        entity.statement = request.statement
        val response = taskRepository.save(entity)
        logger.info("Statement has been changed")
        return response.convertToTaskDTO()
    }

    fun changeDescription(request: ChangeDescriptionRequest): Task {
        val entity = taskRepository.findTaskEntityById(request.id)
        entity.description = request.description
        val response = taskRepository.save(entity)
        logger.info("Description has been changed")
        return response.convertToTaskDTO()
    }

    fun changeName(request: ChangeNameRequest): Task {
        val entity = taskRepository.findTaskEntityById(request.id)
        entity.name = request.name
        val response = taskRepository.save(entity)
        logger.info("Name has been changed")
        return response.convertToTaskDTO()
    }

    fun changeEmployee(request: ChangeEmployeeRequest): Task {
        val entity = taskRepository.findTaskEntityById(request.id)
        entity.employee = userRepository.getUserEntityById(request.employee)
        val response = taskRepository.save(entity)
        return response.convertToTaskDTO()
    }

    fun changePriority(request: ChangePriorityRequest): Task {
        val entity = taskRepository.findTaskEntityById(request.id)
        entity.priority = request.priority
        val response = taskRepository.save(entity)
        return response.convertToTaskDTO()
    }

    @Transactional
    fun deleteTask(id: UUID): String {
        taskRepository.deleteTaskEntityById(id)
        return "Task $id successful"
    }

}