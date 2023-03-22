package sg.edu.nus.iss.day17workshop.model;

import java.io.Serializable;

import jakarta.json.JsonNumber;
import jakarta.json.JsonObject;

public class Main implements Serializable {
    
    private double temp;

    public double getTemp() {
        return temp;
    }

    public void setTemp(double temp) {
        this.temp = temp;
    }
    
    public static Main jsonObjectToJavaObject(JsonObject o) {
        Main main = new Main();
        JsonNumber jsonNumber = o.getJsonNumber("temp");
        main.setTemp(jsonNumber.doubleValue());
        return main;
    }

}
