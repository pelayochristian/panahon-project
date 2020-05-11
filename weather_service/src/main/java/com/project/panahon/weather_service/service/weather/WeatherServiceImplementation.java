package com.project.panahon.weather_service.service.weather;

import com.project.panahon.weather_service.cache.RedisCacheManager;
import com.project.panahon.weather_service.service.weather.source.OpenWeather;
import com.project.panahon.weather_service.service.weather.factory.Weather;
import com.project.panahon.weather_service.service.weather.factory.WeatherFactory;
import com.project.panahon.weather_service.service.weather.factory.WeatherSourceTypes;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.env.Environment;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

/**
 * Service class that implements the method from {@link WeatherService} <br>
 * interface. This service will handle the scraping of data of individual<br>
 * sources.
 *
 * @author christian
 * @since 2020-05-09
 */
@Service
public class WeatherServiceImplementation implements WeatherService {

    private RedisCacheManager redisCacheManager;
    private Environment environment;

    @Value("${api.openweather.api.key_1}")
    private String openWeatherApi;

    private int openWeatherCounter = 1;

    @Autowired
    public WeatherServiceImplementation(RedisCacheManager redisCacheManager,
                                        Environment environment) {
        this.redisCacheManager = redisCacheManager;
        this.environment = environment;
    }

    /**
     * Service function that implemented the method from {@link WeatherService}
     * interface to fetch data from <tt>https://openweathermap.org/api</tt>.
     *
     * @param latitude  {@link String}
     * @param longitude {@link String}
     * @return {@link Map}
     */
    @SuppressWarnings("unchecked")
    @Override
    public Map<String, Object> openWeather(String latitude, String longitude) {

        // Cache type.
        String cacheType = latitude + longitude;

        // Check OpenWeather data is exist in the cache.
        Map<String, Object> cacheMap = redisCacheManager
                .obtainCache(cacheType, OpenWeather.class.getSimpleName(), Map.class);

        // If exist in the cache, return the data from the cache.
        if (cacheMap != null) {
            return cacheMap;
        } else { // Else call function to do API call the openWeather API, then return.
            return callOpenWeather(latitude, longitude, redisCacheManager);
        }
    }


    /**
     * Service function that handle the business logic for calling <br>
     * the OpenWeather API.
     *
     * @param latitude     {@link String}
     * @param longitude    {@link String}
     * @param cacheManager {@link RedisCacheManager}
     * @return {@link Map}
     */
    public Map<String, Object> callOpenWeather(String latitude, String longitude, RedisCacheManager cacheManager) {

        openWeatherCounter++;

        // Json Response holder.
        Map<String, Object> jsonResponse = new HashMap<>();

        // Get the url from the property file.
        String url = Objects.requireNonNull(environment.getProperty("api.openweather.hourly.forecast.url"));

        // Construct the URL use for API Call.
        String urlString = String.format(url, openWeatherApi, latitude, longitude);

        // Use REST-Template to get the response.
        RestTemplate restTemplate = new RestTemplate();

        try {
            // Get the response entity and assign as a ResponseEntity Object
            ResponseEntity<Object> responseEntity = restTemplate.getForEntity(urlString, Object.class);

            // Get the parse object.
            Weather weather = WeatherFactory.getWeatherDataParser(WeatherSourceTypes.OPENWEATHER);

            // Get response json, use to save into the cache.
            jsonResponse = weather.parseWeatherData(responseEntity);

            // Cache type
            String type = latitude + longitude;

            // Add to cache.
            cacheManager.putCache(type, OpenWeather.class.getSimpleName(), jsonResponse);

            // Add expire time.
            cacheManager.obtainExpire(type);
        } catch (Exception e) {
            switch (openWeatherCounter) {
                case 2:
                    openWeatherApi = environment.getProperty("api.openweather.api.key_2");
                    return callOpenWeather(latitude, longitude, cacheManager);
                case 3:
                    openWeatherApi = environment.getProperty("api.openweather.api.key_3");
                    return callOpenWeather(latitude, longitude, cacheManager);
                default:
                    openWeatherCounter = 0;
                    jsonResponse.put(OpenWeather.class.getSimpleName(), e.toString());
                    break;
            }
        }

        return jsonResponse;
    }
}
