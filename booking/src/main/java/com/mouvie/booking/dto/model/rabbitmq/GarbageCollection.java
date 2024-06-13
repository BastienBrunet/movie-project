package com.mouvie.booking.dto.model.rabbitmq;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class GarbageCollection {

    @JsonProperty("fullsweep_after")
    private int fullsweepAfter;

    @JsonProperty("max_heap_size")
    private int maxHeapSize;

    @JsonProperty("min_bin_vheap_size")
    private int minBinVheapSize;

    @JsonProperty("min_heap_size")
    private int minHeapSize;

    @JsonProperty("minor_gcs")
    private int minorGcs;
}
