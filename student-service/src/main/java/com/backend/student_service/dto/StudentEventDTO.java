package com.backend.student_service.dto;


import lombok.Data;

@Data
public class StudentEventDTO {

    private String eventType; //create, update, delete
    private Integer studentId;
    private String studentCode;
    private String fullName;
    private String email;
}
