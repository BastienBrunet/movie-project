package com.mouvie.booking.dto.model.rabbitmq;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.Map;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class ConsumerDetail {

    @JsonProperty("arguments")
    private Map<String, Object> arguments;

    @JsonProperty("channel_details")
    private ChannelDetail channelDetails;

    @JsonProperty("ack_required")
    private boolean ackRequired;

    @JsonProperty("active")
    private boolean active;

    @JsonProperty("activity_status")
    private String activityStatus;

    @JsonProperty("consumer_tag")
    private String consumerTag;

    @JsonProperty("consumer_timeout")
    private int consumerTimeout;

    @JsonProperty("exclusive")
    private boolean exclusive;

    @JsonProperty("prefetch_count")
    private int prefetchCount;

    @JsonProperty("queue")
    private Queue queue;

}
