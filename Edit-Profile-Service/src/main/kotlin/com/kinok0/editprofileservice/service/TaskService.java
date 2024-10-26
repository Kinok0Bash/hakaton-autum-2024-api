package com.kinok0.editprofileservice.service;

import com.kinok0.editprofileservice.entity.TaskEntity;
import com.kinok0.editprofileservice.repository.TaskRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TaskService {
    private TaskRepository taskRepository;


    public TaskService(TaskRepository taskRepository) {
        this.taskRepository = taskRepository;
    }


    public List<TaskEntity> findAll() {
        return taskRepository.findAll();
    }

}
