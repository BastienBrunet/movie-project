package com.mouvie.booking.dto.model.rabbitmq;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.List;
import java.util.Map;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class QueueDetails {

    @JsonProperty("consumer_details")
    private List<ConsumerDetail> consumerDetails;

    @JsonProperty("arguments")
    private Map<String, Object> arguments;

    @JsonProperty("auto_delete")
    private boolean autoDelete;

    @JsonProperty("consumer_capacity")
    private int consumerCapacity;

    @JsonProperty("consumer_utilisation")
    private double consumerUtilisation;

    @JsonProperty("consumers")
    private int consumers;

    @JsonProperty("deliveries")
    private List<Object> deliveries;

    @JsonProperty("durable")
    private boolean durable;

    @JsonProperty("effective_policy_definition")
    private Map<String, Object> effectivePolicyDefinition;

    @JsonProperty("exclusive")
    private boolean exclusive;

    @JsonProperty("exclusive_consumer_tag")
    private String exclusiveConsumerTag;

    @JsonProperty("garbage_collection")
    private GarbageCollection garbageCollection;

    @JsonProperty("head_message_timestamp")
    private String headMessageTimestamp;

    @JsonProperty("idle_since")
    private String idleSince;

    @JsonProperty("incoming")
    private List<Object> incoming;

    @JsonProperty("memory")
    private int memory;

    @JsonProperty("message_bytes")
    private int messageBytes;

    @JsonProperty("message_bytes_paged_out")
    private int messageBytesPagedOut;

    @JsonProperty("message_bytes_persistent")
    private int messageBytesPersistent;

    @JsonProperty("message_bytes_ram")
    private int messageBytesRam;

    @JsonProperty("message_bytes_ready")
    private int messageBytesReady;

    @JsonProperty("message_bytes_unacknowledged")
    private int messageBytesUnacknowledged;

    @JsonProperty("messages")
    private int messages;

    @JsonProperty("messages_details")
    private MessageDetails messagesDetails;

    @JsonProperty("messages_paged_out")
    private int messagesPagedOut;

    @JsonProperty("messages_persistent")
    private int messagesPersistent;

    @JsonProperty("messages_ram")
    private int messagesRam;

    @JsonProperty("messages_ready")
    private int messagesReady;

    @JsonProperty("messages_ready_details")
    private MessageDetails messagesReadyDetails;

    @JsonProperty("messages_ready_ram")
    private int messagesReadyRam;

    @JsonProperty("messages_unacknowledged")
    private int messagesUnacknowledged;

    @JsonProperty("messages_unacknowledged_details")
    private MessageDetails messagesUnacknowledgedDetails;

    @JsonProperty("messages_unacknowledged_ram")
    private int messagesUnacknowledgedRam;

    @JsonProperty("name")
    private String name;

    @JsonProperty("node")
    private String node;

    @JsonProperty("operator_policy")
    private String operatorPolicy;

    @JsonProperty("policy")
    private String policy;

    @JsonProperty("recoverable_slaves")
    private String recoverableSlaves;

    @JsonProperty("reductions")
    private int reductions;

    @JsonProperty("reductions_details")
    private MessageDetails reductionsDetails;

    @JsonProperty("single_active_consumer_tag")
    private String singleActiveConsumerTag;

    @JsonProperty("state")
    private String state;

    @JsonProperty("storage_version")
    private int storageVersion;

    @JsonProperty("type")
    private String type;

    @JsonProperty("vhost")
    private String vhost;

}
