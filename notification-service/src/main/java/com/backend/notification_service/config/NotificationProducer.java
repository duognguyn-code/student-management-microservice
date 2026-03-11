package com.backend.notification_service.config;

import com.backend.notification_service.dto.NotificationEventDTO;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
public class NotificationProducer {
    private final KafkaTemplate<String, NotificationEventDTO> kafkaTemplate;

    public NotificationProducer(KafkaTemplate<String, NotificationEventDTO> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    public void sendNotification(NotificationEventDTO event){

        kafkaTemplate.send("notification-topic", event);

    }
}
