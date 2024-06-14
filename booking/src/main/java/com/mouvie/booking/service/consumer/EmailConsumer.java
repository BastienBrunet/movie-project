package com.mouvie.booking.service.consumer;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;

@Service
public class EmailConsumer {

    // Make this handle list of emails
    @RabbitListener(queues = {"q.email"})
    public void consume(String message) {
        System.out.println("EmailConsumer: " + message);
    }
}
