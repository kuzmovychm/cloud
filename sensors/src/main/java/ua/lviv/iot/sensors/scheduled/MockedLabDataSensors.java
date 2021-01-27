package ua.lviv.iot.sensors.scheduled;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import ua.lviv.iot.sensors.model.KinesisWrapper;

@Log4j2
@Component
@RequiredArgsConstructor
public class MockedLabDataSensors {
    private final RestTemplate restTemplate;

    @Value("${api.new-url}")
    private String URL;

    @Scheduled(fixedDelay = 40)
    public void put() {
        KinesisWrapper wrapper = new KinesisWrapper();
        log.info(wrapper.getData());
        restTemplate.exchange(URL, HttpMethod.PUT, new HttpEntity(wrapper), Void.class);
    }
}
