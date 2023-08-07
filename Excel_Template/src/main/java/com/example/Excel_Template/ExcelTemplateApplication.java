package com.example.Excel_Template;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.web.servlet.WebMvcAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
@ComponentScan
@Configuration
@SpringBootApplication
public class ExcelTemplateApplication  {
//	@Override
//	public void addCorsMappings(CorsRegistry registry) {
//		registry.addMapping("/**")
//				.allowedOrigins("http://localhost:63342")
//				.allowedMethods("GET", "POST", "PUT", "DELETE");
//	}
	public static void main(String[] args) {
		SpringApplication.run(ExcelTemplateApplication.class, args);
	}

}
