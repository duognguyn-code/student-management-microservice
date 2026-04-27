package com.backend.student_service.dto;


import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@Getter
@Setter
public class StudentEventDTO {

    private String eventType; //create, update, delete
    private Integer studentId;
    private String studentCode;
    private String fullName;
    private String email;
}
