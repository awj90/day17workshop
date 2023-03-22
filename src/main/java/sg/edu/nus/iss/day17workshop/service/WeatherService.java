package sg.edu.nus.iss.day17workshop.service;

import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import sg.edu.nus.iss.day17workshop.model.Country;

import java.io.IOException;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;

@Service
public class WeatherService {
    
    @Value("${workshop17.url}")
    private String openWeatherUrl; // https://api.openweathermap.org/data/2.5/weather

    @Value("${workshop17.api.key}")
    private String openWeatherApiKey; 

    public Optional<Country> getWeather(String city, String units) throws IOException{
        // construct full URL
        String fullUrl = UriComponentsBuilder
                            .fromUriString(openWeatherUrl)
                            .queryParam("q", city.replaceAll(" ", "+"))
                            .queryParam("appid", openWeatherApiKey)
                            .queryParam("units", units)
                            .toUriString();
        // city.replace all spaces with + for cities with two words (eg. Buenos Aires or Hong Kong)

        // instantiate RestTemplate
        RestTemplate restTemplate = new RestTemplate();

        ResponseEntity<String> resp = restTemplate.getForEntity(fullUrl, String.class);

        Country c = Country.jsonStringToJsonObject(resp.getBody());
        if (c!=null) {
            return Optional.of(c);
        } else {
            return Optional.empty();
        }
    }
}
