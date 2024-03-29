package com.stackroute.springbootconsumerexample.config;


import com.stackroute.springbootconsumerexample.receiver.Receiver;
import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.annotation.EnableKafka;
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory;
import org.springframework.kafka.config.KafkaListenerContainerFactory;
import org.springframework.kafka.core.ConsumerFactory;
import org.springframework.kafka.core.DefaultKafkaConsumerFactory;
import org.springframework.kafka.listener.ConcurrentMessageListenerContainer;

import java.util.HashMap;
import java.util.Map;

@EnableKafka
@Configuration
public class ReceiverConfig {

    private static String BOOTSTRAP_SERVERS_CONFIG;
    private static String KEY_DESERIALIZER_CLASS_CONFIG ;
    private static String VALUE_DESERIALIZER_CLASS_CONFIG;
    private static String GROUP_ID_CONFIG;
    private static String AUTO_OFFSET_RESET_CONFIG="earliest";

    @Value("${kafka.bootstrap-servers}")
    private String bootstrapServers;

    @Bean
    public Map<String, Object> consumerConfigs() {
        Map<String, Object> config = new HashMap<>();

        // list of host:port pairs used for establishing the initial connections to the Kafka cluster

        config.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG,
                "localhost:9092");
        config.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG,
                StringDeserializer.class);
        config.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG,
                StringDeserializer.class);

        // allows a pool of processes to divide the work of consuming and processing records

        config.put(ConsumerConfig.GROUP_ID_CONFIG, "myTopic");

        // automatically reset the offset to the earliest offset

        config.put(ConsumerConfig.AUTO_OFFSET_RESET_CONFIG, "earliest");

        return config;
    }

    @Bean
    public ConsumerFactory<String, String> consumerFactory() {
        return new DefaultKafkaConsumerFactory<>(consumerConfigs());
    }

    @Bean
    public ConcurrentKafkaListenerContainerFactory<String, String> kafkaListenerContainerFactory() {
        ConcurrentKafkaListenerContainerFactory<String, String> factory =
                new ConcurrentKafkaListenerContainerFactory<>();
        factory.setConsumerFactory(consumerFactory());

        return factory;
    }

    @Bean
    public Receiver receiver() {

        return new Receiver();
    }

}
