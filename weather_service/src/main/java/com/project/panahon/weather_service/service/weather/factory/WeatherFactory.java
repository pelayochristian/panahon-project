package com.project.panahon.weather_service.service.weather.factory;

import com.project.panahon.weather_service.service.weather.source.OpenWeather;

/**
 * This will serve a factory class the will <br>
 * return what {@link WeatherSourceTypes} to return.
 *
 * @author christian
 * @since 2020-05-09
 */
public class WeatherFactory {

    /**
     * Function use for returning what {@link WeatherSourceTypes} to <br>
     * return.
     *
     * @param weatherSourceTypes The {@link WeatherSourceTypes}
     * @return The {@link Weather}
     */
    public static Weather getWeatherDataParser(WeatherSourceTypes weatherSourceTypes) {
        Weather weather = null;
        switch (weatherSourceTypes) {
            case OPENWEATHER:
                weather = new OpenWeather();
                break;
            default:
                break;
        }
        return weather;
    }
}
