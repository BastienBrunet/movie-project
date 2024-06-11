package com.mouvie.booking.service.consumer;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;

@Service
public class ReservationConsumer {

    @RabbitListener(queues = {"q.reservation"})
    public void consume(String message) {
        System.out.println("ReservationConsumer: " + message);
    }

}
