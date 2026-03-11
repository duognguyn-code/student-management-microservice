package com.backend.notification_service.dto;

import lombok.Data;

@Data
public class NotificationEventDTO {
    private Integer studentId;
    private String status;
}
