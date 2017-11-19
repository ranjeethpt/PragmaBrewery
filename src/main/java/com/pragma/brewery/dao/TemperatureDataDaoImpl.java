package com.pragma.brewery.dao;

import com.pragma.brewery.model.TemperatureData;
import org.springframework.context.annotation.Profile;

import java.util.concurrent.ThreadLocalRandom;

/**
 * Created by ranjeethpt on 19/11/17.
 *
 * @author ranjeethpt
 */
@Profile("!unitTest")
public class TemperatureDataDaoImpl implements TemperatureDataDao {

    @Override
    public TemperatureData getCurrentTemperature(String id) {
        return new TemperatureData(id, ThreadLocalRandom.current().nextDouble(3, 8));
    }
}
