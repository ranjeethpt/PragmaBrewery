package com.pragma.brewery.service;

/**
 * Created by ranjeethpt on 19/11/17.
 *
 * @author ranjeethpt
 */
public interface TemperatureIdentifierService {
    Boolean isTemperatureInRange(Double currentTemperature, Double minRange, Double maxRange);
}
