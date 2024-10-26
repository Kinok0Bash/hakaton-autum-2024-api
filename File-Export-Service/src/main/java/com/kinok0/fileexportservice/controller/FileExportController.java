package com.kinok0.fileexportservice.controller;


import com.kinok0.fileexportservice.entity.TaskEntity;
import com.kinok0.fileexportservice.entity.UserEntity;
import com.kinok0.fileexportservice.service.TaskService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

@RestController
@RequestMapping("/api/exportfile")
public class FileExportController {
   // private UserService userService;
    private TaskService taskService;
   //private CommentService commentService;


    public FileExportController(/*UserService userService,*/ TaskService taskService/*, CommentService commentService*/) {
        //this.userService = userService;
        this.taskService = taskService;
      //  this.commentService = commentService;
    }
//
//    @GetMapping("/users")
//    public List<UserEntity> getAllUser(){
//        return userService.findAll();
//    }
//
//
//    @GetMapping("/tasks")
//    public List<TaskEntity> getAllTask(){
//        return taskService.findAll();
//    }
//
//    @PostMapping()
//    public ResponseEntity<HttpStatus> init() {
//        userService.init();
//        taskService.init();
//        commentService.init();
//
//        return new ResponseEntity<>(HttpStatus.CREATED);
//    }

    @GetMapping()
    public ResponseEntity<byte[]> exportFile() {
        byte[] excelFile = taskService.exportTasksToExcel();
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_OCTET_STREAM);

        String nameFile = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy.MM.dd__HH.mm"));
        String typeFile = ".xlsx";

        headers.setContentDispositionFormData("attachment", nameFile + typeFile);
        return new ResponseEntity<>(excelFile, headers, HttpStatus.OK);
    }

}
