package com.kinok0.fileexportservice.repository;

import com.kinok0.fileexportservice.entity.TaskEntity;
import com.kinok0.fileexportservice.entity.UserEntity;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.UUID;

public interface TaskRepository extends CrudRepository<TaskEntity, UUID> {

    @Override
    List<TaskEntity> findAll();
}
