package com.backend.notification_service.config;

import com.backend.notification_service.dto.StudentEventDTO;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
public class NotificationConsumer {
    @KafkaListener(topics = "student-created-topic", groupId = "notification-group")
    public void receiveStudent(StudentEventDTO event){

        System.out.println("Send email for student: " + event.getFullName());

    }
}
