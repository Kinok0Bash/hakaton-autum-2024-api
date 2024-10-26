//package com.kinok0.fileexportservice.service;
//
//import com.kinok0.fileexportservice.entity.CommentEntity;
//import com.kinok0.fileexportservice.entity.Role;
//import com.kinok0.fileexportservice.entity.TaskEntity;
//import com.kinok0.fileexportservice.entity.UserEntity;
//import com.kinok0.fileexportservice.repository.CommentRepository;
//import com.kinok0.fileexportservice.repository.TaskRepository;
//import com.kinok0.fileexportservice.repository.UserRepository;
//import lombok.RequiredArgsConstructor;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Service;
//import org.springframework.transaction.annotation.Transactional;
//
//import java.util.List;
//
//@Service
//public class CommentService {
//    private CommentRepository commentRepository;
//    private UserRepository userRepository;
//    private TaskRepository taskRepository;
//
//    public CommentService(CommentRepository commentRepository, UserRepository userRepository, TaskRepository taskRepository) {
//        this.commentRepository = commentRepository;
//        this.userRepository = userRepository;
//        this.taskRepository = taskRepository;
//    }
//
//    public List<CommentEntity> findAll() {
//        return commentRepository.findAll();
//    }
//
//    @Transactional()
//    public void init() {
//        List<UserEntity> users = userRepository.findAll();
//        List<TaskEntity> tasks = taskRepository.findAll();
//
//        int i = 0;
//
//        commentRepository.save(new CommentEntity("Initial design draft completed.", users.get(i), tasks.get(i++)));
//        commentRepository.save(new CommentEntity("API endpoints defined.", users.get(i+1), tasks.get(i)));
//        commentRepository.save(new CommentEntity("Data cleanup in progress.", users.get(i), tasks.get(i+1)));
//        commentRepository.save(new CommentEntity("Project plan approved by management.", users.get(i++), tasks.get(i)));
//    }
//}
