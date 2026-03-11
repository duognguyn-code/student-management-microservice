package com.backend.student_service.dto;

import lombok.Data;

@Data
public class NotificationEventDTO {
    private Integer studentId;
    private String status;
}
