package com.housing.app.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping(value = "/health",
        produces = {"application/json"})
public class HealthcheckController {

    @GetMapping
    public ResponseEntity checkHealth() {
        Map result = new HashMap();
        result.put("status", "Application is running");
        return new ResponseEntity(result, HttpStatus.OK);

    }
}
