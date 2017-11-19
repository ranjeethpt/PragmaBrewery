package com.pragma.brewery.service;

/**
 * Created by ranjeethpt on 19/11/17.
 *
 * @author ranjeethpt
 */
public class TemperatureDataNotFoundException extends RuntimeException {
    public TemperatureDataNotFoundException(String message) {
        super(message);
    }
}
