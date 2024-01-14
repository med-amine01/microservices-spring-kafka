package m.chebbi.tech.emailservice.kafka;

import m.chebbi.tech.basedomains.dto.OrderEvent;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
public class OrderConsumer {

    @KafkaListener(topics = "${spring.kafka.topic.name}", groupId = "${spring.kafka.consumer.group-id}")
    public void consume(OrderEvent event) {
        System.err.printf("Order EVENT received => %s", event.toString());
    }
}
