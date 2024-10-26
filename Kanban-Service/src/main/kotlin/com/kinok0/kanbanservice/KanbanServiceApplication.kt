package com.kinok0.kanbanservice

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.data.jpa.repository.config.EnableJpaRepositories

@SpringBootApplication
@EnableJpaRepositories(basePackages = ["com.kinok0.kanbanservice.repository"])
class KanbanServiceApplication

fun main(args: Array<String>) {
	runApplication<KanbanServiceApplication>(*args)
}
