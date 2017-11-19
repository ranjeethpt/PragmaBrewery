package com.pragma.brewery.service;

import com.pragma.brewery.model.TemperatureData;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Profile;

import static com.google.common.base.Preconditions.checkNotNull;

/**
 * Created by ranjeethpt on 19/11/17.
 *
 * @author ranjeethpt
 */
@Profile("!unitTest")
@Slf4j
public class NotifyMessageServiceImpl implements NotifyMessageService {

    @Override
    public void notify(TemperatureData temperatureData, Double minRange, Double maxRange) {
        checkNotNull(temperatureData);
        log.info("Temperature of {} is out of range. Range should be between {} & {} but current range is {}.",
                temperatureData.getId(), minRange, maxRange, temperatureData.getCurrentTemperature());
    }
}
