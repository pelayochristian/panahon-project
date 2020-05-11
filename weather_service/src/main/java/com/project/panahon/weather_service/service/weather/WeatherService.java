package com.project.panahon.weather_service.service.weather;

import java.util.Map;

public interface WeatherService {
    Map<String, Object> openWeather(String latitude, String longitude);
}
