package com.pragma.brewery.service;

import com.pragma.brewery.dao.Drinks;
import com.pragma.brewery.model.TemperatureData;

import java.util.List;

import static com.google.common.base.Preconditions.checkNotNull;

/**
 * Created by ranjeethpt on 19/11/17.
 *
 * @author ranjeethpt
 */
public class TemperatureMonitorServiceImpl implements TemperatureMonitorService {

    private final ReadTemperatureService readTemperatureService;
    private final TemperatureIdentifierService temperatureIdentifierService;
    private final NotifyMessageService notifyMessageService;
    private final List<Drinks> drinks;

    public TemperatureMonitorServiceImpl(ReadTemperatureService readTemperatureService, TemperatureIdentifierService temperatureIdentifierService,
                                         NotifyMessageService notifyMessageService, List<Drinks> drinks) {
        checkNotNull(readTemperatureService);
        checkNotNull(temperatureIdentifierService);
        checkNotNull(notifyMessageService);
        checkNotNull(drinks);

        this.readTemperatureService = readTemperatureService;
        this.temperatureIdentifierService = temperatureIdentifierService;
        this.notifyMessageService = notifyMessageService;
        this.drinks = drinks;
    }


    @Override
    public void monitor() {
        drinks.forEach(drinks -> {
            TemperatureData temperatureData = readTemperatureService.readCurrentTemperature(drinks.getId());
            Boolean inRange = temperatureIdentifierService.isTemperatureInRange(temperatureData.getCurrentTemperature(), drinks.getMinRange(), drinks.getMaxRange());
            if (!inRange) {
                notifyMessageService.notify(temperatureData, drinks.getMinRange(), drinks.getMaxRange());
            }
        });
    }
}
