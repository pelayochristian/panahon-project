package com.project.panahon.weather_service.service.weather.factory;

import org.springframework.http.ResponseEntity;

import java.util.Map;

/**
 * This abstract class will be the parent of all weather
 * provider Instance.
 *
 * @author christian
 * @since 2020-05-02
 */
public abstract class Weather {
    public abstract Map<String, Object> parseWeatherData(ResponseEntity<Object> responseEntity);
}
