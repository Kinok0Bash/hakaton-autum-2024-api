package com.kinok0.profileservice

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.cloud.client.discovery.EnableDiscoveryClient
import org.springframework.data.jpa.repository.config.EnableJpaRepositories

@SpringBootApplication
@EnableDiscoveryClient
@EnableJpaRepositories(basePackages = ["com.kinok0.profileservice.repository"])
class ProfileServiceApplication

fun main(args: Array<String>) {
    runApplication<ProfileServiceApplication>(*args)
}
