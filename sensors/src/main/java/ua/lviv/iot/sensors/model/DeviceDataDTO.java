package ua.lviv.iot.sensors.model;

import lombok.Getter;
import lombok.ToString;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

@Getter
@ToString
public class DeviceDataDTO {
    private static final Random random = new Random();

    private final String event;
    private final String device;
    private final String timestamp;

    public DeviceDataDTO() {
        event = LabData.EVENTS.get(random.nextInt(LabData.EVENTS.size()));
        device = LabData.DEVICES.get(random.nextInt(LabData.DEVICES.size()));
        String dateString = LocalDateTime.now().toString();
//        timestamp = dateString.substring(0, dateString.indexOf("."));
        timestamp = dateString.replace("T", " ");
    }

    private static class LabData {
        private static final List<String> DEVICES = Arrays.asList("PC", "PRINTER", "HI-FI");
        private static final List<String> EVENTS = Arrays.asList("START", "STOP", "RESTART");
    }
}
