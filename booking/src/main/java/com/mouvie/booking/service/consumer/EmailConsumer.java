package com.mouvie.booking.service.consumer;

import com.mouvie.library.dto.email.EmailTemplate;
import com.mouvie.library.service.email.EmailService;
import lombok.AllArgsConstructor;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class EmailConsumer {

    private final EmailService emailService;

    // Make this handle list of emails
    @RabbitListener(queues = {"q.email"})
    public void consume(List<EmailTemplate> emails) {
        emails.forEach(emailService::sendSimpleMessage);
    }
}
