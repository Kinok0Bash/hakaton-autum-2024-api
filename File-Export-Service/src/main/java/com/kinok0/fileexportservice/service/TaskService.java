package com.kinok0.fileexportservice.service;

import com.kinok0.fileexportservice.entity.TaskEntity;
import com.kinok0.fileexportservice.repository.TaskRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.stereotype.Service;

import java.io.ByteArrayOutputStream;
import java.util.List;

@Service
@AllArgsConstructor
@Slf4j
public class TaskService {
    private TaskRepository taskRepository;

    public byte[] exportTasksToExcel() {
        List<TaskEntity> tasks = taskRepository.findAll();
        Workbook workbook = new XSSFWorkbook();
        Sheet sheet = workbook.createSheet("Tasks");

        // Устанавливаем ширину столбца
        sheet.setColumnWidth(0, 200 * 256 / 7);
        sheet.setColumnWidth(1, 500 * 256 / 7);
        sheet.setColumnWidth(2, 300 * 256 / 7);
        sheet.setColumnWidth(3, 500 * 256 / 7);

        // Создание стиля для заголовков
        CellStyle headerStyle = workbook.createCellStyle();
        Font headerFont = workbook.createFont();
        headerFont.setBold(true);
        headerFont.setFontHeightInPoints((short) (12 + 2)); // Увеличиваем размер шрифта на 2 пункта
        headerStyle.setFont(headerFont);
        headerStyle.setWrapText(true);  // Включаем автоперенос текста

        // Добавляем границы к заголовку
        headerStyle.setBorderTop(BorderStyle.THIN);
        headerStyle.setBorderBottom(BorderStyle.THIN);
        headerStyle.setBorderLeft(BorderStyle.THIN);
        headerStyle.setBorderRight(BorderStyle.THIN);

        Row headerRow = sheet.createRow(0);
        Cell headerCell = headerRow.createCell(0);
        headerCell.setCellValue("Название");
        headerCell.setCellStyle(headerStyle);
        headerRow.createCell(1).setCellValue("Описание");
        headerRow.getCell(1).setCellStyle(headerStyle);
        headerRow.createCell(2).setCellValue("Исполнитель");
        headerRow.getCell(2).setCellStyle(headerStyle);
        headerRow.createCell(3).setCellValue("Комментарии");
        headerRow.getCell(3).setCellStyle(headerStyle);

        // Создаем стиль с границами и автопереносом для данных
        CellStyle dataStyle = workbook.createCellStyle();
        dataStyle.setBorderTop(BorderStyle.THIN);
        dataStyle.setBorderBottom(BorderStyle.THIN);
        dataStyle.setBorderLeft(BorderStyle.THIN);
        dataStyle.setBorderRight(BorderStyle.THIN);
        dataStyle.setWrapText(true);  // Включаем автоперенос текста

        int rowCount = 1;
        StringBuffer sb = new StringBuffer();
        for (TaskEntity task : tasks) {
            sb.setLength(0);

            Row row = sheet.createRow(rowCount++);
            Cell nameCell = row.createCell(0);
            nameCell.setCellValue(convertHtmlTextToText(task.getName()));
            nameCell.setCellStyle(dataStyle);

            Cell descriptionCell = row.createCell(1);
            descriptionCell.setCellValue(convertHtmlTextToText(task.getDescription()));
            descriptionCell.setCellStyle(dataStyle);

            Cell employeeCell = row.createCell(2);
            if (task.getEmployee() != null) {
                employeeCell.setCellValue(task.getEmployee().getName());
            } else {
                employeeCell.setCellValue("Не назначен");
            }
            employeeCell.setCellStyle(dataStyle);

            task.getComments().stream().forEach(o -> sb.append(o.getUser().getName() + ": " + o.getComment() + "; "));
            Cell commentsCell = row.createCell(3);
            commentsCell.setCellValue(sb.toString());
            commentsCell.setCellStyle(dataStyle);
        }

        try (ByteArrayOutputStream outputStream = new ByteArrayOutputStream()) {
            workbook.write(outputStream);
            workbook.close();
            return outputStream.toByteArray();
        } catch (Exception e) {
            log.error(e.getMessage());
        }
        return null;
    }

    private String convertHtmlTextToText(String htmlText){
        return htmlText.replaceAll("<[^>]+>", "");
    }


}
