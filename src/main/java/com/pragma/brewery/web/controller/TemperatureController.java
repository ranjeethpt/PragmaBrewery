package com.pragma.brewery.web.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.pragma.brewery.model.TemperatureData;
import com.pragma.brewery.service.ReadTemperatureService;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import static com.google.common.base.Preconditions.checkNotNull;

/**
 * Created by ranjeethpt on 19/11/17.
 *
 * @author ranjeethpt
 */

@RestController
@RequestMapping("/services")
public class TemperatureController {

    private final ReadTemperatureService readTemperatureService;

    public TemperatureController(ReadTemperatureService readTemperatureService) {
        checkNotNull(readTemperatureService);

        this.readTemperatureService = readTemperatureService;
    }

    @RequestMapping(value = "/temperature/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<TemperatureData> getCurrentTemperature(@PathVariable String id) throws JsonProcessingException {
        return new ResponseEntity<>(readTemperatureService.readCurrentTemperature(id), HttpStatus.OK);
    }

}
