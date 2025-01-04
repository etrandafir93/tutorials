package com.baeldung.embedded.kafka.reset;


import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.test.EmbeddedKafkaBroker;

import static com.baeldung.embedded.kafka.TestUtils.*;
import static java.util.Collections.singletonList;
import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.kafka.test.utils.KafkaTestUtils.getRecords;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class FooIntegrationTest extends BaseKafkaTest {

    @Autowired
    EmbeddedKafkaBroker kafka;

    @Test
    void whenPublishingMessage_thenFindItAsSingleKafkaRecord() throws Exception {
        try (var producer = testKafkaProducer(kafka); var consumer = testKafkaConsumer(kafka)) {
            consumer.subscribe(singletonList("test-topic"));
            producer.send(new ProducerRecord<>("test-topic", "foo")).get();

            var records = getRecords(consumer).records("test-topic");
            assertThat(records)
                    .map(ConsumerRecord::value)
                    .contains("foo");
        }
    }

    @AfterEach
    void after() {
        System.err.println("qq" + kafka.getBrokersAsString());
        try (var consumer = testKafkaConsumer(kafka)){
            consumer.subscribe(singletonList("test-topic"));
            getRecords(consumer)
                    .records("test-topic")
                    .forEach(it -> System.err.println("xxx => " + it));
        }
    }
}
