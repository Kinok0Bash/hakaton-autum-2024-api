package com.kinok0.editprofileservice.service;

import com.kinok0.editprofileservice.entity.CommentEntity;
import com.kinok0.editprofileservice.entity.TaskEntity;
import com.kinok0.editprofileservice.entity.UserEntity;
import com.kinok0.editprofileservice.repository.CommentRepository;
import com.kinok0.editprofileservice.repository.TaskRepository;
import com.kinok0.editprofileservice.repository.UserRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class CommentService {

    private CommentRepository commentRepository;
    private UserRepository userRepository;
    private TaskRepository taskRepository;

    public CommentService(CommentRepository commentRepository, UserRepository userRepository, TaskRepository taskRepository) {
        this.commentRepository = commentRepository;
        this.userRepository = userRepository;
        this.taskRepository = taskRepository;
    }

    public List<CommentEntity> findAll() {
        return commentRepository.findAll();
    }


}
