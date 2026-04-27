package com.backend.student_service.config;

import com.backend.student_service.dto.StudentEventDTO;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;
import com.fasterxml.jackson.databind.ObjectMapper;

@Service
public class StudentProducer {
    private final KafkaTemplate<String, String> kafkaTemplate;
    private final ObjectMapper objectMapper = new ObjectMapper();

    public StudentProducer(KafkaTemplate<String, String> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    public void sendStudentEvent(StudentEventDTO event) {
        try {
            String json = objectMapper.writeValueAsString(event);
            kafkaTemplate.send("student-created-topic", json);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
