package com.kinok0.editprofileservice.repository;

import com.kinok0.editprofileservice.entity.TaskEntity;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.UUID;

public interface TaskRepository extends CrudRepository<TaskEntity, UUID> {

    @Override
    List<TaskEntity> findAll();
}
