package com.example.Excel_Template.repository;

import com.example.Excel_Template.Entity.template_data;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TemplateDatarepository extends JpaRepository<template_data,Long> {
    List<template_data> findByOrganization(String organization);
    }


