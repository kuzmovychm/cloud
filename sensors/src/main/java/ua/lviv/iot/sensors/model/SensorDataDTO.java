package ua.lviv.iot.sensors.model;

import lombok.Data;
import lombok.ToString;

@Data
@ToString
public class SensorDataDTO {
    private Double longitude;
    private Double latitude;
    private String timestamp;
    private String data;
}
