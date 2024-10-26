package com.kinok0.editprofileservice.repository;

import com.kinok0.editprofileservice.entity.CommentEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface CommentRepository extends CrudRepository<CommentEntity, UUID> {

    @Override
    List<CommentEntity> findAll();
}
