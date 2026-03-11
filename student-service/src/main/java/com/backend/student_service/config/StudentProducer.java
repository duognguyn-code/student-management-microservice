package com.backend.student_service.config;

import com.backend.student_service.dto.StudentEventDTO;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
public class StudentProducer {
    private final KafkaTemplate<String, StudentEventDTO> kafkaTemplate;

    public StudentProducer(KafkaTemplate<String, StudentEventDTO> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    public void sendStudentEvent(StudentEventDTO event){
        kafkaTemplate.send("student-created-topic", event);
    }
}
