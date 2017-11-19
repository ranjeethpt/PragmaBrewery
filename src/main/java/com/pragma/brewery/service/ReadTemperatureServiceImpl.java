package com.pragma.brewery.service;

import com.pragma.brewery.dao.Drinks;
import com.pragma.brewery.dao.TemperatureDataDao;
import com.pragma.brewery.model.TemperatureData;

import java.util.List;
import java.util.stream.Collectors;

import static com.google.common.base.Preconditions.checkNotNull;

/**
 * Created by ranjeethpt on 19/11/17.
 *
 * @author ranjeethpt
 */
public class ReadTemperatureServiceImpl implements ReadTemperatureService {

    private final TemperatureDataDao temperatureDataDao;
    private final List<Drinks> drinks;

    public ReadTemperatureServiceImpl(TemperatureDataDao temperatureDataDao, List<Drinks> drinks) {
        checkNotNull(temperatureDataDao);
        checkNotNull(drinks);

        this.temperatureDataDao = temperatureDataDao;
        this.drinks = drinks;
    }

    public TemperatureData readCurrentTemperature(String name) {
        checkNotNull(name);

        List<TemperatureData> temperatureDataResult = drinks.stream().filter(temperatureData -> name.equals(temperatureData.getId())).collect(Collectors.toList());
        if (temperatureDataResult.isEmpty()) {

            throw new TemperatureDataNotFoundException(String.format("%s is not present in our records. Available Drinks are %s.", name, drinks.stream()
                    .map(Drinks::getId).collect(Collectors.joining(","))));
        }
        return temperatureDataDao.getCurrentTemperature(name);
    }
}
