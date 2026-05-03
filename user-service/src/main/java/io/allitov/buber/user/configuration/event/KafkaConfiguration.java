package io.allitov.buber.user.configuration.event;

import io.allitov.buber.user.core.event.DriverAvailableEvent;
import java.util.HashMap;
import java.util.Map;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.common.serialization.StringSerializer;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.core.DefaultKafkaProducerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.core.ProducerFactory;
import org.springframework.kafka.support.serializer.JacksonJsonSerializer;
import tools.jackson.databind.json.JsonMapper;

/**
 * Конфигурация Kafka.
 */
@Configuration
public class KafkaConfiguration {

    @Value("${spring.kafka.bootstrap-servers}")
    private String bootstrapServers;

    @Bean
    ProducerFactory<String, DriverAvailableEvent> producerFactory(JsonMapper jsonMapper) {
        Map<String, Object> config = new HashMap<>();
        config.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, bootstrapServers);
        config.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class);
        config.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, JacksonJsonSerializer.class);

        return new DefaultKafkaProducerFactory<>(
                config, new StringSerializer(), new JacksonJsonSerializer<>(jsonMapper));
    }

    @Bean
    KafkaTemplate<String, DriverAvailableEvent> kafkaTemplate(
            ProducerFactory<String, DriverAvailableEvent> producerFactory) {
        return new KafkaTemplate<>(producerFactory);
    }
}
