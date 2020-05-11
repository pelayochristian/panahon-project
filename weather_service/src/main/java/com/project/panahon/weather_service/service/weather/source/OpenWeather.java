package com.project.panahon.weather_service.service.weather.source;

import com.project.panahon.weather_service.service.weather.factory.Weather;
import org.springframework.http.ResponseEntity;

import java.util.*;

/**
 * This class extends the parent abstract class {@link Weather} <br>
 * to parse the incoming response then return it back.
 *
 * @author christian
 * @since 2020-05-09
 */
public class OpenWeather extends Weather {

    /**
     * Override method from the parent abstract class {@link Weather}
     *
     * @param responseEntity The {@link ResponseEntity}
     * @return The {@link Map}
     */
    @SuppressWarnings("unchecked")
    @Override
    public Map<String, Object> parseWeatherData(ResponseEntity<Object> responseEntity) {

        // Holder for the selected response.
        Map<String, Object> childMap = new HashMap<>();

        // Holder fot the childMap
        Map<String, Object> parentMap = new HashMap<>();

        // Get the list of response map.
        List<LinkedHashMap<String, Object>> bodyListResponse =
                (List<LinkedHashMap<String, Object>>)
                        ((LinkedHashMap<String, LinkedHashMap<String, LinkedHashMap<String, Object>>>)
                                Objects.requireNonNull(responseEntity.getBody()))
                                .get("list");

        // TODO use simple ranking to get the best of 5 from the response.
        childMap.put("0", bodyListResponse.get(1));
        childMap.put("1", bodyListResponse.get(2));
        childMap.put("2", bodyListResponse.get(3));
        childMap.put("3", bodyListResponse.get(4));
        childMap.put("4", bodyListResponse.get(5));

        // Add the child map to the parent then return.
        parentMap.put(this.getClass().getSimpleName(), childMap);
        return parentMap;
    }
}
