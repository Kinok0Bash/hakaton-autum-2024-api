package com.kinok0.authenticationservice

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.autoconfigure.domain.EntityScan
import org.springframework.boot.runApplication
import org.springframework.cloud.client.discovery.EnableDiscoveryClient
import org.springframework.data.jpa.repository.config.EnableJpaRepositories

@SpringBootApplication
@EnableDiscoveryClient
@EnableJpaRepositories(basePackages = ["com.kinok0.authenticationservice.repository"])
class AuthenticationServiceApplication

fun main(args: Array<String>) {
	runApplication<AuthenticationServiceApplication>(*args)
}
