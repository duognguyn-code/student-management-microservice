package com.backend.student_service.config;

import com.backend.student_service.dto.NotificationEventDTO;
import org.springframework.kafka.annotation.KafkaListener;

public class StudentConsumer {
    @KafkaListener(topics = "notification-topic", groupId = "student-group")
    public void receiveNotification(NotificationEventDTO event){

        System.out.println("Notification received for student: " + event.getStudentId());

    }
}
