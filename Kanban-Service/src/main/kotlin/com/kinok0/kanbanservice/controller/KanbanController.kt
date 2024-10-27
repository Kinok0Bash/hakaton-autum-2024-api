package com.kinok0.kanbanservice.controller

import com.kinok0.kanbanservice.dto.KanbanColumn
import com.kinok0.kanbanservice.dto.Task
import com.kinok0.kanbanservice.dto.request.*
import com.kinok0.kanbanservice.service.KanbanService
import org.slf4j.LoggerFactory
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import java.util.*

@RestController
@RequestMapping("/api/kanban")
class KanbanController(private val kanbanService: KanbanService) {
    private val logger = LoggerFactory.getLogger(KanbanController::class.java)

    @GetMapping("/")
    fun getKanban(): ResponseEntity<ArrayList<KanbanColumn>> {
        logger.info("Request to get kanban!")
        return ResponseEntity.ok(kanbanService.getKanban())
    }

    @GetMapping("/{id}")
    fun getTaskById(@PathVariable id: UUID): ResponseEntity<Task> {
        logger.info("Try get task with id: $id!")
        return ResponseEntity.ok(kanbanService.getTaskById(id))
    }

    @PostMapping("/create")
    fun createTask(@RequestBody request: TaskCreateRequest): ResponseEntity<ArrayList<KanbanColumn>> {
        logger.info("Request to creating task")
        return ResponseEntity.ok(kanbanService.createTask(request))
    }

    @PutMapping("/change-statement")
    fun changeStatement(@RequestBody request: ChangeStatementRequest): ResponseEntity<Task> {
        logger.info("Request for changing statement for ${request.id} task to ${request.statement}")
        return ResponseEntity.ok(kanbanService.changeStatement(request))
    }

    @PutMapping("/change-description")
    fun changeDescription(@RequestBody request: ChangeDescriptionRequest): ResponseEntity<Task> {
        logger.info("Request for changing description for task ${request.id}")
        return ResponseEntity.ok(kanbanService.changeDescription(request))
    }

    @PutMapping("/change-name")
    fun changeName(@RequestBody request: ChangeNameRequest): ResponseEntity<Task> {
        logger.info("Request for changing name for task ${request.id}")
        return ResponseEntity.ok(kanbanService.changeName(request))
    }

    @PutMapping("/change-employee")
    fun changeEmployee(@RequestBody request: ChangeEmployeeRequest): ResponseEntity<Task> {
        logger.info("Request for changing employee for task ${request.id}")
        return ResponseEntity.ok(kanbanService.changeEmployee(request))
    }

    @PutMapping("/change-priority")
    fun changePriority(@RequestBody request: ChangePriorityRequest): ResponseEntity<Task> {
        logger.info("Request for changing priority for task ${request.id} to ${request.priority}")
        return ResponseEntity.ok(kanbanService.changePriority(request))
    }

    @DeleteMapping("/{id}")
    fun deleteTask(@PathVariable id: UUID): ResponseEntity<Map<String, String>> {
        logger.info("Request to deleting task $id")
        return ResponseEntity.ok(mapOf("message" to kanbanService.deleteTask(id)))
    }

    @ExceptionHandler
    fun handleException(ex: Exception) : ResponseEntity<*> {
        logger.error("Exception: $ex")
        return ResponseEntity.badRequest().body(mapOf("error" to "${ex.message}"))
    }

}