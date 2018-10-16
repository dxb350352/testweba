package com.dxb;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class KafkaReceiver {

    @KafkaListener(topics = {"dxb_topic"})
    public void listen1(ConsumerRecord<?, ?> record) {

        Optional<?> kafkaMessage = Optional.ofNullable(record.value());

        if (kafkaMessage.isPresent()) {

            Object message = kafkaMessage.get();

            System.out.println("1----------------- record =" + record);
            System.out.println("1------------------ message =" + message);
        }

    }

    @KafkaListener(topics = {"dxb_topic"})
    public void listen2(ConsumerRecord<?, ?> record) {

        Optional<?> kafkaMessage = Optional.ofNullable(record.value());

        if (kafkaMessage.isPresent()) {

            Object message = kafkaMessage.get();

            System.out.println("2----------------- record =" + record);
            System.out.println("2------------------ message =" + message);
        }

    }
}