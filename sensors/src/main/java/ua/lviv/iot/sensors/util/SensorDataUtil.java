package ua.lviv.iot.sensors.util;

import ua.lviv.iot.sensors.model.SensorDataDTO;

public class SensorDataUtil {
    public static SensorDataDTO createMotionData() {
        SensorDataDTO dataDTO = createBase();
        dataDTO.setData("-23.43 64.23 -> -23.43 65.23");
        return dataDTO;
    }

    public static SensorDataDTO createSpeedData() {
        SensorDataDTO dataDTO = createBase();
        dataDTO.setData("300km/h");
        return dataDTO;
    }

    public static SensorDataDTO createSmokeData() {
        SensorDataDTO dataDTO = createBase();
        dataDTO.setData("NO_SMOKE");
        return dataDTO;
    }

    private static SensorDataDTO createBase() {
        SensorDataDTO dataDTO = new SensorDataDTO();
        dataDTO.setLatitude(-23.43);
        dataDTO.setLongitude(65.23);
        dataDTO.setTimestamp("2020-12-15 03:39:01");
        return dataDTO;
    }
}
