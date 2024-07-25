package org.example.hostelbooking.listener;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.example.hostelbooking.entity.BookingData;
import org.example.hostelbooking.entity.RegistrationData;
import org.example.hostelbooking.service.BookingDataService;
import org.example.hostelbooking.service.RegistrationDataService;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Slf4j
@Component
@RequiredArgsConstructor
public class KafkaMessageListener {

    private final RegistrationDataService registrationDataService;

    private final BookingDataService bookingDataService;

    @KafkaListener(topics = "${app.kafka.registrationMessageTopic}",
            groupId = "${app.kafka.kafkaMessageGroupId}",
            containerFactory = "registrationDataConcurrentKafkaListenerContainerFactory")
    public void listenRegistration(@Payload RegistrationData message,
                       @Header(value = KafkaHeaders.RECEIVED_KEY, required = false) UUID key,
                       @Header(KafkaHeaders.RECEIVED_TOPIC) String topic,
                       @Header(KafkaHeaders.RECEIVED_PARTITION) Integer partition,
                       @Header(KafkaHeaders.RECEIVED_TIMESTAMP) Long timestamp) {

        log.info("Received message: {}", message);
        log.info("Key: {}; Partition: {}; Topic: {}; Timestamp: {}", key, partition, topic, timestamp);

        registrationDataService.add(message);
    }


    @KafkaListener(topics = "${app.kafka.bookingMessageTopic}",
            groupId = "${app.kafka.kafkaMessageGroupId}",
            containerFactory = "bookingDataConcurrentKafkaListenerContainerFactory")
    public void listenBooking(@Payload BookingData message,
                       @Header(value = KafkaHeaders.RECEIVED_KEY, required = false) UUID key,
                       @Header(KafkaHeaders.RECEIVED_TOPIC) String topic,
                       @Header(KafkaHeaders.RECEIVED_PARTITION) Integer partition,
                       @Header(KafkaHeaders.RECEIVED_TIMESTAMP) Long timestamp) {

        log.info("Received message: {}", message);
        log.info("Key: {}; Partition: {}; Topic: {}; Timestamp: {}", key, partition, topic, timestamp);

        bookingDataService.add(message);
    }
}
