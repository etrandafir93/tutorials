package com.baeldung.embedded.kafka;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.apache.kafka.common.serialization.StringSerializer;
import org.springframework.kafka.test.EmbeddedKafkaBroker;
import org.springframework.kafka.test.utils.KafkaTestUtils;

import java.util.Map;
import java.util.UUID;

import static java.time.Duration.ofSeconds;
import static org.apache.kafka.clients.consumer.ConsumerConfig.*;
import static org.apache.kafka.clients.producer.ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG;
import static org.apache.kafka.clients.producer.ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG;
import static org.springframework.kafka.test.utils.KafkaTestUtils.getOneRecord;
import static org.springframework.kafka.test.utils.KafkaTestUtils.getRecords;

public class TestUtils {

    public static ConsumerRecord<?, ?> pollForOneRecord(EmbeddedKafkaBroker kafka, String topic) {
        return getOneRecord(kafka.getBrokersAsString(), "test-group-id", topic, 0, false, true, ofSeconds(5));
    }

    public static KafkaConsumer<String, String> testKafkaConsumer(EmbeddedKafkaBroker kafka) {
        Map<String, Object> props = KafkaTestUtils.consumerProps(UUID.randomUUID().toString(), "true", kafka);
        props.put(KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class.getName());
        props.put(VALUE_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class.getName());
        props.put(AUTO_OFFSET_RESET_CONFIG, "earliest");
        return new KafkaConsumer<>(props);
    }

    public static KafkaProducer<String, String> testKafkaProducer(EmbeddedKafkaBroker kafka) {
        Map<String, Object> props = KafkaTestUtils.producerProps(kafka);
        props.put(KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class.getName());
        props.put(VALUE_SERIALIZER_CLASS_CONFIG, StringSerializer.class.getName());
        return new KafkaProducer<>(props);
    }
}
