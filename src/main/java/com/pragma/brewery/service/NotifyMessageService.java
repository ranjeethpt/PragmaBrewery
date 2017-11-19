package com.pragma.brewery.service;

import com.pragma.brewery.model.TemperatureData;

/**
 * Created by ranjeethpt on 19/11/17.
 *
 * @author ranjeethpt
 */
public interface NotifyMessageService {
    void notify(TemperatureData temperatureData, Double minRange, Double MaxRange);
}
