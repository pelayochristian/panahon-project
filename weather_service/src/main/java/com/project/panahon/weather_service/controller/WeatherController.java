package com.project.panahon.weather_service.controller;

import com.project.panahon.weather_service.service.weather.WeatherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping(path = "/weather")
public class WeatherController {

    private WeatherService weatherService;

    @Autowired
    public WeatherController(WeatherService weatherService) {
        this.weatherService = weatherService;
    }

    @GetMapping(path = "/open-weather", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Map<String, Object>> obtainOpenWeather
            (@RequestParam String latitude,
             @RequestParam String longitude) {
        return new ResponseEntity<>(weatherService.openWeather(latitude, longitude), HttpStatus.OK);
    }
}
