package com.mouvie.booking.dto.model.rabbitmq;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class ChannelDetail {

    @JsonProperty("connection_name")
    private String connectionName;

    @JsonProperty("name")
    private String name;

    @JsonProperty("node")
    private String node;

    @JsonProperty("number")
    private int number;

    @JsonProperty("peer_host")
    private String peerHost;

    @JsonProperty("peer_port")
    private int peerPort;

    @JsonProperty("user")
    private String user;

}
