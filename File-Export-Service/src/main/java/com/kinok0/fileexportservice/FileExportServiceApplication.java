package com.kinok0.fileexportservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
//@EnableDiscoveryClient
@EnableJpaRepositories("com.kinok0.fileexportservice.repository")
public class FileExportServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(FileExportServiceApplication.class, args);
    }

}
