package ua.lviv.iot.sensors.scheduled;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import ua.lviv.iot.sensors.model.SensorDataDTO;
import ua.lviv.iot.sensors.util.SensorDataUtil;

@Log4j2
@Component
@RequiredArgsConstructor
public class MockedSensors {
    private final RestTemplate restTemplate;

    @Value("${api.url}")
    private String apiUrl;

    @Scheduled(fixedDelay = 100)
    public void sendMotionData() {
        SensorDataDTO dataDTO = SensorDataUtil.createMotionData();
        post(dataDTO);
    }

    @Scheduled(fixedDelay = 80)
    public void sendSpeedData() {
        SensorDataDTO dataDTO = SensorDataUtil.createSpeedData();
        post(dataDTO);
    }

    @Scheduled(fixedDelay = 60)
    public void sendSmokeData() {
        SensorDataDTO dataDTO = SensorDataUtil.createSmokeData();
        post(dataDTO);
    }

    private void post(SensorDataDTO dataDTO) {
        HttpEntity entity = new HttpEntity(dataDTO);
        log.info("Sending sensor data: {}", dataDTO);
        restTemplate.exchange(apiUrl, HttpMethod.POST, entity, void.class);
    }
}
