package m.chebbi.tech.orderservice.kafka;

import m.chebbi.tech.basedomains.dto.OrderEvent;
import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.messaging.Message;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Service;

@Service

public class OrderProducer {
    
    private NewTopic topic;
    private KafkaTemplate<String, OrderEvent> kafkaTemplate;
    
    public OrderProducer(NewTopic topic, KafkaTemplate<String, OrderEvent> kafkaTemplate) {
        this.topic = topic;
        this.kafkaTemplate = kafkaTemplate;
    }
    
    public void sendMessage(OrderEvent event) {
        System.err.println(String.format("Order event => %s", event.toString()));
        
        // Create message
        Message<OrderEvent> message = MessageBuilder
                .withPayload(event)
                .setHeader(KafkaHeaders.TOPIC, topic.name())
                .build();
        
        // Send message to kafka
        
        kafkaTemplate.send(message);
    }
}
