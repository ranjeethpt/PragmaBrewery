package com.pragma.brewery.service;

import com.pragma.brewery.model.TemperatureData;

/**
 * Created by ranjeethpt on 19/11/17.
 *
 * @author ranjeethpt
 */
public interface ReadTemperatureService {
    TemperatureData readCurrentTemperature(String name);
}
