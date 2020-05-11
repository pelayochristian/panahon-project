package com.project.panahon.gateway_service.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

/**
 * REST-End point for service fallback. If there <br>
 * something happen to a certain service, this calls <br>
 * serve as a call back rest -end point.
 *
 * @author christian
 * @since 2020-05-10
 */
@RestController
public class FallbackController {

    /**
     * REST-End point for handling the fallback of <br>
     * weather service.
     *
     * @return {@link ResponseEntity}
     */
    @GetMapping(path = "/weatherFallback")
    public ResponseEntity<Map<String, Object>> weatherServiceFallback() {
        Map<String, Object> responseMessage = new HashMap<>();
        responseMessage.put("message", "Weather service is taking too long to respond or is down. " +
                "Please try again.");
        return new ResponseEntity<>(responseMessage, HttpStatus.OK);
    }
}
