package m.chebbi.tech.orderservice.controller;

import m.chebbi.tech.basedomains.dto.Order;
import m.chebbi.tech.basedomains.dto.OrderEvent;
import m.chebbi.tech.orderservice.kafka.OrderProducer;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RestController
@RequestMapping("/api/v1")
public class OrderController {
    
    private final OrderProducer orderProducer;
    
    public OrderController(OrderProducer orderProducer) {
        this.orderProducer = orderProducer;
    }
    
    @PostMapping("/orders")
    public String placeOrder(@RequestBody Order order) {
        order.setOrderId(UUID.randomUUID().toString());
        
        OrderEvent orderEvent = new OrderEvent();
        orderEvent.setStatus("PENDING");
        orderEvent.setMessage("order status is pending state");
        orderEvent.setOrder(order);
        // Sending order
        orderProducer.sendMessage(orderEvent);
        
        return "order placed successfully";
    }
}
