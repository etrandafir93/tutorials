package com.baeldung.embedded.kafka.reset;

import com.baeldung.embedded.kafka.Application;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.kafka.test.context.EmbeddedKafka;

@SpringBootTest(classes = Application.class)
@EmbeddedKafka(partitions = 1, topics = {"test-topic"})
abstract class BaseKafkaTest {
}
