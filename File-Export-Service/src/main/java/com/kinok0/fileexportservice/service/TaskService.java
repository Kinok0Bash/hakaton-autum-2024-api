package com.kinok0.fileexportservice.service;

import com.kinok0.fileexportservice.entity.TaskEntity;
import com.kinok0.fileexportservice.repository.TaskRepository;
import org.apache.poi.ss.usermodel.*;
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

        // Создание стиля для заголовков
        CellStyle headerStyle = workbook.createCellStyle();
        Font headerFont = workbook.createFont();
        headerFont.setBold(true);
        headerFont.setFontHeightInPoints((short) (12 + 2)); // Увеличиваем размер шрифта на 2 пункта
        headerStyle.setFont(headerFont);

        Row headerRow = sheet.createRow(0);
        Cell headerCell = headerRow.createCell(0);
        headerCell.setCellValue("Title");
        headerCell.setCellStyle(headerStyle); // Применение стиля к заголовку
        headerRow.createCell(1).setCellValue("Description");
        headerRow.getCell(1).setCellStyle(headerStyle);
        headerRow.createCell(2).setCellValue("Responsible for the task");
        headerRow.getCell(2).setCellStyle(headerStyle);
        headerRow.createCell(3).setCellValue("Comments");
        headerRow.getCell(3).setCellStyle(headerStyle);


        int rowCount = 1;
        StringBuffer sb = new StringBuffer();
        for (TaskEntity task : tasks) {
            sb.setLength(0);

            Row row = sheet.createRow(rowCount++);
            row.createCell(0).setCellValue(task.getName());
            row.createCell(1).setCellValue(task.getDescription());
            row.createCell(2).setCellValue(task.getEmployee().getName());

            task.getComments().stream().forEach(o-> sb.append(o.getUser().getName()+ ": "+ o.getComment() + "; "));

            row.createCell(3).setCellValue(sb.toString());
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
