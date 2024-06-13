package com.mouvie.booking.service.rabbitmq;


import com.mouvie.booking.dto.model.rabbitmq.QueueDetails;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.tomcat.util.codec.binary.Base64;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;


@Service
@NoArgsConstructor
@Slf4j
public class RabbitMqService implements IRabbitMqService {

    @Value("${spring.rabbitmq.host}")
    private String rabbitHost;

    @Value("${spring.rabbitmq.username}")
    private String rabbitUser;

    @Value("${spring.rabbitmq.password}")
    private String rabbitPassword;

    // %s is the queue name
    private static final String QUEUE_DETAIL_PATH = ":15672/api/queues/%2f/";

    public QueueDetails getQueueInformations(String queueName) {

        String fullUrl = "http://"+ rabbitHost + QUEUE_DETAIL_PATH + queueName;
        URI uri = URI.create(fullUrl);

        RestClient restClient = buildAuthenticatedRestClient();

        try {
            log.info("GET : " + uri);

            ResponseEntity<QueueDetails> response = restClient.get()
                    .uri(uri)
                    .retrieve()
                    .toEntity(QueueDetails.class);

            log.info(response.toString());
            return response.getBody();

        } catch (Exception e) {
            log.error(e.getMessage());
            throw e;
        }

    }

    private RestClient buildAuthenticatedRestClient() {
        String encodedCredentials = getEncodedCredentials();

        return RestClient
                .builder()
                .defaultHeader("Authorization", encodedCredentials)
                .build();
    }

    private String getEncodedCredentials() {
        String credentials = rabbitUser + ":" + rabbitPassword;
        return "Basic " + Base64.encodeBase64String(credentials.getBytes());
    }
}
