package com.mouvie.booking.dto.model.rabbitmq;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class Queue {

    @JsonProperty("name")
    private String name;

    @JsonProperty("vhost")
    private String vhost;
}
