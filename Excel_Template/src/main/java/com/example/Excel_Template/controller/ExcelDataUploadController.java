//package com.example.Excel_Template.controller;
//
////import ch.qos.logback.core.model.Model;
//import org.springframework.ui.Model;
//
//import com.example.Excel_Template.Entity.template_data;
//import com.example.Excel_Template.repository.TemplateDatarepository;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.http.ResponseEntity;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.PostMapping;
//import org.springframework.web.bind.annotation.RequestParam;
//import org.springframework.web.bind.annotation.RestController;
//import org.springframework.web.multipart.MultipartFile;
//
//import java.util.List;
//
//@RestController
//public class ExcelDataUploadController {
//    @Autowired
//    private TemplateDatarepository templateDataRepository;
//
//    // Endpoint to handle file upload and save data to the database
//    @PostMapping("/upload-template")
//    public ResponseEntity<String> uploadTemplate(@RequestParam("file") MultipartFile file) {
//        // Read the data from the uploaded Excel file and save it to the database
//        // ...
//
//        return ResponseEntity.ok("Data uploaded successfully");
//    }
//
//    // Endpoint to display uploaded data in the table
//    @GetMapping("/view-uploaded-data")
//    public String viewUploadedData(@RequestParam("organization") String organization, Model model) {
//        List<template_data> data = templateDataRepository.findByOrganization(organization);
//        model.addAttribute("data", data);
//
//        return "uploaded_data_table";
//    }
//}
