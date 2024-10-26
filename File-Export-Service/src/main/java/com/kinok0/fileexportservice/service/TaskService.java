package com.kinok0.fileexportservice.service;

import com.kinok0.fileexportservice.entity.TaskEntity;
import com.kinok0.fileexportservice.repository.TaskRepository;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.stereotype.Service;

import java.io.ByteArrayOutputStream;
import java.util.List;

@Service
public class TaskService {
    private TaskRepository taskRepository;


    public TaskService(TaskRepository taskRepository) {
        this.taskRepository = taskRepository;
    }



    public byte[] exportTasksToExcel() {
        List<TaskEntity> tasks = taskRepository.findAll();
        Workbook workbook = new XSSFWorkbook();
        Sheet sheet = workbook.createSheet("Tasks");

        Row header = sheet.createRow(0);
        header.createCell(0).setCellValue("Title");
        header.createCell(1).setCellValue("Title HTML");
        header.createCell(2).setCellValue("Description");
        header.createCell(3).setCellValue("Fio and login executor");
        header.createCell(4).setCellValue("Comments");

        int rowCount = 1;
        StringBuffer sb = new StringBuffer();
        for (TaskEntity task : tasks) {
            sb.setLength(0);

            Row row = sheet.createRow(rowCount++);
            row.createCell(0).setCellValue(task.getName());
            row.createCell(1).setCellValue(task.getHtmlName());
            row.createCell(2).setCellValue(task.getDescription());
            row.createCell(3).setCellValue(task.getEmployee().getName() + ", Log:" + task.getEmployee().getLogin());

            task.getComments().stream().forEach(o-> sb.append(o.getComment()));

            row.createCell(4).setCellValue(sb.toString());
        }

        try (ByteArrayOutputStream outputStream = new ByteArrayOutputStream()) {
            workbook.write(outputStream);
            workbook.close();
            return outputStream.toByteArray();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

}
