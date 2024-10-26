package com.kinok0.editprofileservice.controller;


import com.kinok0.editprofileservice.dto.request.UserAvatarRequest;
import com.kinok0.editprofileservice.dto.request.UserNameRequest;
import com.kinok0.editprofileservice.dto.request.UserPositionRequest;
import com.kinok0.editprofileservice.dto.response.UserResponse;
import com.kinok0.editprofileservice.entity.TaskEntity;
import com.kinok0.editprofileservice.entity.UserEntity;
import com.kinok0.editprofileservice.service.TaskService;
import com.kinok0.editprofileservice.service.CommentService;
import com.kinok0.editprofileservice.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/edit-profile")
public class EditProfileController {
    private UserService userService;
    private TaskService taskService;
    private CommentService commentService;


    public EditProfileController(UserService userService, TaskService taskService, CommentService commentService) {
        this.userService = userService;
        this.taskService = taskService;
        this.commentService = commentService;
    }

    @GetMapping("/users")
    public List<UserEntity> getAllUser(){
        return userService.findAll();
    }


    @GetMapping("/tasks")
    public List<TaskEntity> getAllTask(){
        return taskService.findAll();
    }

    @PostMapping()
    public ResponseEntity<HttpStatus> init() {

        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @PutMapping("/change-name")
    public ResponseEntity<UserResponse> changeName(@RequestBody UserNameRequest userRequest) {
        return ResponseEntity.ok(userService.changeName(userRequest));
    }

    @PutMapping("/change-position")
    public ResponseEntity<UserResponse> changePosition(@RequestBody UserPositionRequest userRequest) {
        return ResponseEntity.ok(userService.changePosition(userRequest));
    }

    @PutMapping("/change-avatar")
    public ResponseEntity<UserResponse> changeAvatar(@RequestBody UserAvatarRequest userRequest) {
        return ResponseEntity.ok(userService.changeAvatar(userRequest));
    }




}
