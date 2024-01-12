package m.chebbi.tech.orderservice.config;

import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.TopicBuilder;

@Configuration
public class KafkaTopicConfig {
    
    @Value("${spring.kafka.topic.name}")
    private String topicName;
    
    @Bean
    public NewTopic topic() {
        // You could add partitions name("test").partitions(3)
        return TopicBuilder
                .name(topicName)
                .build();
    }
}
