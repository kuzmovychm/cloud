package ua.lviv.iot.sensors.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.ToString;

@Getter
public class KinesisWrapper {
    @JsonProperty("Data")
    private final DeviceDataDTO data;

    @JsonProperty("PartitionKey")
    private final String partitionKey;

    public KinesisWrapper() {
        data = new DeviceDataDTO();
        partitionKey = "mocked key";
    }
}
