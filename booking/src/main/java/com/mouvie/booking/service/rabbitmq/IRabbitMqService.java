package com.mouvie.booking.service.rabbitmq;

import com.mouvie.booking.dto.model.rabbitmq.QueueDetails;

public interface IRabbitMqService {

    QueueDetails getQueueInformations(String queueName);
}
