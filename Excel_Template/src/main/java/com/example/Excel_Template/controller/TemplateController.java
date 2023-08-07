package com.example.Excel_Template.controller;

import com.example.Excel_Template.Entity.template_data;
import com.example.Excel_Template.repository.TemplateDatarepository;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.apache.poi.ss.usermodel.*;
//import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import javax.xml.crypto.Data;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.*;

@Controller
//@RestController
@CrossOrigin(origins = "http://localhost:63342/")
public class TemplateController {
    @Autowired

    private TemplateDatarepository templateDataRepository;

    // Endpoint to display template creation form
    @GetMapping("/create-template")
    public String showTemplateForm(Model model) {
        // Provide necessary data to the template, if needed
        // ...
        List<template_data> dataList = templateDataRepository.findAll();
        model.addAttribute("dataList", dataList);

        return "template_form";
    }

    // Endpoint to handle form submission and create dynamic Excel template
    @PostMapping("/create-template")
    public String generateDynamicTemplate(@RequestParam("fieldMapping") Map<String, String> fieldMapping, Model model) {
        // Generate the dynamic Excel template using Apache POI
        Workbook workbook = new HSSFWorkbook();
        Sheet sheet = workbook.createSheet("Template Sheet");

        // Create the header row
        Row headerRow = sheet.createRow(0);
        int columnCount = 0;
        for (String uiColumnName : fieldMapping.keySet()) {
            Cell cell = headerRow.createCell(columnCount++);
            cell.setCellValue(uiColumnName);
        }

        // You can set additional properties, styles, or data validation rules as needed

        // Write the workbook to a byte array
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        try {
            workbook.write(outputStream);
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                workbook.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        byte[] excelData = outputStream.toByteArray();

        // Save the byte array in the model to be accessible in the view
        model.addAttribute("excelData", excelData);

        return "template_form";
    }

    // Endpoint to download the dynamic Excel template
    @PostMapping("/download-template")
    public ResponseEntity<Resource> downloadTemplate(@RequestParam("excelData") byte[] excelData) {
        // Return the Excel template as a downloadable file
//        byte[] excelData = generateDynamicExcelData();
        ByteArrayResource resource = new ByteArrayResource(excelData);

        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=dynamic_template.xlsx")
                .contentType(MediaType.APPLICATION_OCTET_STREAM)
                .body(resource);
    }

    // Endpoint to handle file upload and save data to the database
    @PostMapping("/upload-template")
    public ResponseEntity<String> uploadTemplate(@RequestParam("file") MultipartFile file) {
        if (file.isEmpty()) {
            return ResponseEntity.badRequest().body("Please select a file to upload.");
        }

        try {
            // Read the data from the uploaded Excel file
            Workbook workbook = new HSSFWorkbook(file.getInputStream());
            Sheet sheet = workbook.getSheetAt(0);

            // Skip the header row (assuming it's the first row)
            Iterator<Row> rowIterator = sheet.iterator();
            if (rowIterator.hasNext()) {
                rowIterator.next();
            }

            // Save the data to the database
            List<template_data> dataList = new ArrayList<>();
            while (rowIterator.hasNext()) {
                Row row = rowIterator.next();
                template_data data = new template_data();
                data.setOrganization("YourOrganizationName"); // Set the organization name here
                data.setFirstName(row.getCell(0).getStringCellValue());
                data.setLastName(row.getCell(1).getStringCellValue());
                data.setDateOfBirth(row.getCell(2).getStringCellValue());
                data.setCity(row.getCell(3).getStringCellValue());
                // Add other fields as per requirement
                // ...

                dataList.add(data);
            }

            templateDataRepository.saveAll(dataList);

            workbook.close();
        } catch (IOException e) {
            e.printStackTrace();
            return ResponseEntity.status(500).body("Error processing the uploaded file.");
        }

        return ResponseEntity.ok("Data uploaded successfully");
    }

    // Endpoint to display uploaded data in the table
    @GetMapping("/view-uploaded-data")
    public String viewUploadedData(@RequestParam("organization") String organization, Model model) {
        List<template_data> data = templateDataRepository.findByOrganization(organization);
        model.addAttribute("data", data);

        return "uploaded_data_table";
    }
}
