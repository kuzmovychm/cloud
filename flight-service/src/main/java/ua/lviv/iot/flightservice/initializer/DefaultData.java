package ua.lviv.iot.flightservice.initializer;

import ua.lviv.iot.flightservice.entity.Airline;
import ua.lviv.iot.flightservice.entity.Landmark;
import ua.lviv.iot.flightservice.entity.airplane.Airplane;
import ua.lviv.iot.flightservice.entity.race.Race;

import java.util.ArrayList;
import java.util.List;

public class DefaultData {
    public static Airline DEFAULT_AIRLINE;
    public static Airplane DEFAULT_AIRPLANE;
    public static final List<Landmark> DEFAULT_LANDMARKS = new ArrayList<>();

    static {
        DEFAULT_AIRLINE = new Airline("Ukraine International Airlines");

        DEFAULT_AIRPLANE = createDefault();

        DEFAULT_LANDMARKS.add(new Landmark()
                .setName("Lviv")
                .setLatitude(49.8397)
                .setLongitude(24.0297)
        );
        DEFAULT_LANDMARKS.add(new Landmark()
                .setName("Kyiv")
                .setLatitude(50.4501)
                .setLongitude(30.5234)
        );
        DEFAULT_LANDMARKS.add(new Landmark()
                .setName("Odesa")
                .setLatitude(46.4825)
                .setLongitude(30.7233)
        );

    }

    private static Airplane createDefault() {
        Airplane airplane = new Airplane();
        airplane.setIdentifier("ir-32-max");
        airplane.setKilometersPast(1000L);
        airplane.setName("Max's Plane");
        airplane.setSpeed(300D);
        return airplane;
    }
}
