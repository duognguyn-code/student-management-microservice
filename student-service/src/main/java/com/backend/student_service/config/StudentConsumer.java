package com.backend.student_service.config;

import com.backend.student_service.dto.NotificationEventDTO;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;


@Component
public class StudentConsumer {
    @KafkaListener(topics = "notification-topic", groupId = "student-group")
    public void receiveNotification(NotificationEventDTO event){

        System.out.println("Notification received for student: " + event.getStudentId());

    }
    @KafkaListener(topics = "student-events", groupId = "student-group")
    public void handleStudentEvents(String message) {
        System.out.println("Nhận được sự kiện: " + message);

        // Phân tích sự kiện và xử lý
        if (message.startsWith("CREATED:")) {
            String studentId = message.split(":")[1];
            System.out.println("Đang gửi email chào mừng cho sinh viên ID: " + studentId);
            // Logic gửi email ở đây...
        }
    }
}
