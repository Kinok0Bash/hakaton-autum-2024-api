package com.kinok0.fileexportservice.service;

import com.kinok0.fileexportservice.entity.Role;
import com.kinok0.fileexportservice.entity.TaskEntity;
import com.kinok0.fileexportservice.entity.TaskStatement;
import com.kinok0.fileexportservice.entity.UserEntity;
import com.kinok0.fileexportservice.repository.TaskRepository;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.config.Task;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.ByteArrayOutputStream;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

@Service
public class TaskService {
    //private UserRepository userRepository;
    private TaskRepository taskRepository;


    public TaskService(/*UserRepository userRepository,*/ TaskRepository taskRepository) {
       // this.userRepository = userRepository;
        this.taskRepository = taskRepository;
    }

//    public List<TaskEntity> findAll() {
//        return taskRepository.findAll();
//    }
//
//    @Transactional
//    public void init() {
//
//        List<UserEntity> user = userRepository.findAll();
//        int i = 0;
//
//        taskRepository.save(new TaskEntity("Design Homepage", "Create a modern design for the homepage.", user.get(i++), LocalDateTime.now(), TaskStatement.INBOX));
//        taskRepository.save(new TaskEntity("Develop API", "Develop REST API for the new service.", user.get(i++), LocalDateTime.now(), TaskStatement.BACKLOG));
//        taskRepository.save(new TaskEntity("Data Analysis", "Analyze sales data from Q1.", user.get(i++), LocalDateTime.now(), TaskStatement.COMPLETED));
//        taskRepository.save(new TaskEntity("Project Plan", "Outline project plan for upcoming quarter.", user.get(i++), LocalDateTime.now(), TaskStatement.BACKLOG));
//    }

    public byte[] exportTasksToExcel() {
        List<TaskEntity> tasks = taskRepository.findAll();
        Workbook workbook = new XSSFWorkbook();
        Sheet sheet = workbook.createSheet("Tasks");

        Row header = sheet.createRow(0);
        header.createCell(0).setCellValue("Title");
        header.createCell(1).setCellValue("Description");
        header.createCell(2).setCellValue("Fio and login executor");
        header.createCell(3).setCellValue("Comments");

        int rowCount = 1;
        StringBuffer sb = new StringBuffer();
        for (TaskEntity task : tasks) {
            sb.setLength(0);

            Row row = sheet.createRow(rowCount++);
            row.createCell(0).setCellValue(task.getName());
            row.createCell(1).setCellValue(task.getDescription());
            row.createCell(2).setCellValue(task.getEmployee().getName() + ", Log:" + task.getEmployee().getLogin());

            task.getComments().stream().forEach(o-> sb.append(o.getComment()));

            row.createCell(3).setCellValue(sb.toString());
        }

        try (ByteArrayOutputStream outputStream = new ByteArrayOutputStream()) {
            workbook.write(outputStream);
            workbook.close();
            return outputStream.toByteArray();
        } catch (Exception e) {
            // Обработка исключений
        }
        return null;
    }

}
