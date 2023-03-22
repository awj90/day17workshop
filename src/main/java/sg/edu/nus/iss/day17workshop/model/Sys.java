package sg.edu.nus.iss.day17workshop.model;

import java.io.Serializable;

import jakarta.json.JsonNumber;
import jakarta.json.JsonObject;

public class Sys implements Serializable {
    
    private long sunrise;
    private long sunset;

    public long getSunrise() {
        return sunrise;
    }
    public void setSunrise(long sunrise) {
        this.sunrise = sunrise;
    }
    public long getSunset() {
        return sunset;
    }
    public void setSunset(long sunset) {
        this.sunset = sunset;
    }

    public static Sys jsonObjectToJavaObject(JsonObject o) {
        Sys sys = new Sys();
        JsonNumber sunriseTime = o.getJsonNumber("sunrise");
        JsonNumber sunsetTime = o.getJsonNumber("sunset");
        sys.setSunrise(sunriseTime.longValue());
        sys.setSunset(sunsetTime.longValue());
        return sys;
    }
}
