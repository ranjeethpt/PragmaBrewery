package com.pragma.brewery.dao;

import com.pragma.brewery.model.TemperatureData;

/**
 * Created by ranjeethpt on 19/11/17.
 *
 * @author ranjeethpt
 */
public interface TemperatureDataDao {
    TemperatureData getCurrentTemperature(String id);
}
