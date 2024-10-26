package com.kinok0.fileexportservice.controller;

import com.kinok0.fileexportservice.service.TaskService;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@RestController
@RequestMapping("/api/export-file")
@AllArgsConstructor
public class FileExportController {
    private TaskService taskService;

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
