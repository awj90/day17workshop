package sg.edu.nus.iss.day17workshop.controller;

import java.io.IOException;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import sg.edu.nus.iss.day17workshop.model.Country;
import sg.edu.nus.iss.day17workshop.service.WeatherService;

@Controller
public class WeatherController {
    
    @Autowired
    WeatherService weatherService;

    @GetMapping(path="/weather")
    public String getWeather(Model model, @RequestParam(required=true) String q, @RequestParam(defaultValue="metric") String units) throws IOException {
        Optional<Country> c = weatherService.getWeather(q, units); 
        model.addAttribute("country", c.get()); // .get() is required to get the object from the optional
        model.addAttribute("w", c.get().getWeather().get(0));
        return "weather";
    }
}
