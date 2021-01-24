package ua.lviv.iot.lab.demo.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpMethod;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;


@Log4j2
@Component
@RequiredArgsConstructor
public class Loader {
    private static final String HEALTH_PATH = "http://a4cffc8704dd14722aa5ebdccf604353-60032091.us-east-2.elb.amazonaws.com";

    private final RestTemplate restTemplate;

    @Scheduled(fixedDelay = 1)
    public void test1() {
        callApi();
    }

    @Scheduled(fixedDelay = 2)
    public void test2() {
        callApi();
    }

    @Scheduled(fixedDelay = 3)
    public void test3() {
        callApi();
    }

    @Scheduled(fixedDelay = 4)
    public void test4() {
        callApi();
    }

    @Scheduled(fixedDelay = 5)
    public void test5() {
        callApi();
    }

    private void callApi() {
        log.info("Calling api!");
        restTemplate.exchange(HEALTH_PATH, HttpMethod.GET, null, Void.class);
    }
}
