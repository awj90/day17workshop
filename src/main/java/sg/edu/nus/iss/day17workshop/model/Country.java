package sg.edu.nus.iss.day17workshop.model;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.Serializable;
import java.util.LinkedList;
import java.util.List;

import jakarta.json.Json;
import jakarta.json.JsonArray;
import jakarta.json.JsonObject;
import jakarta.json.JsonReader;

public class Country implements Serializable {
    
    private List<Weather> weathers;
    private Main main;
    private Sys sys;
    private long dt;
    private String name;
    
    public List<Weather> getWeather() {
        return weathers;
    }
    public void setWeather(List<Weather> weathers) {
        this.weathers = weathers;
    }
    public Main getMain() {
        return main;
    }
    public void setMain(Main main) {
        this.main = main;
    }
    public Sys getSys() {
        return sys;
    }
    public void setSys(Sys sys) {
        this.sys = sys;
    }
    public long getDt() {
        return dt;
    }
    public void setDt(long dt) {
        this.dt = dt;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }

    public static Country jsonStringToJsonObject(String json) throws IOException {
        Country country = new Country();

        if (json!=null) {
            InputStream is = new ByteArrayInputStream(json.getBytes()); 
            JsonReader jsonReader = Json.createReader(is);
            JsonObject jsonObject = jsonReader.readObject();
            
            country.setName(jsonObject.getString("name"));
            country.setDt(jsonObject.getJsonNumber("dt").longValue());
            country.setSys(Sys.jsonObjectToJavaObject(jsonObject.getJsonObject("sys")));
            country.setMain(Main.jsonObjectToJavaObject(jsonObject.getJsonObject("main")));

            List<Weather> w = new LinkedList<>();
            JsonArray jsonArray = jsonObject.getJsonArray("weather");
            for (int i = 0; i < jsonArray.size(); i++) {
                w.add(Weather.jsonObjectToJavaObject(jsonArray.getJsonObject(i)));
            }

            // Alternatively using lambda:
            // List<Weather> weathers =
            // jsonObject.getJsonArray("weather").stream().map(w -> (JsonObject)w)
            // .map(w -> Weather.jsonObjectToJavaObject(w)).toList();

            country.setWeather(w);
        }
        return country;
    }

}
