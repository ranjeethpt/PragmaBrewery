package com.pragma.brewery.service;

import static com.google.common.base.Preconditions.checkArgument;
import static com.google.common.base.Preconditions.checkNotNull;

/**
 * Created by ranjeethpt on 19/11/17.
 *
 * @author ranjeethpt
 */
public class TemperatureIdentifierServiceImpl implements TemperatureIdentifierService {
    public Boolean isTemperatureInRange(Double currentTemperature, Double minRange, Double maxRange) {
        checkNotNull(currentTemperature, "currentTemperature cannot be null");
        checkNotNull(minRange, "minRange cannot be null");
        checkNotNull(maxRange, "maxRange cannot be null");
        checkArgument(minRange < maxRange, "minRange should be less than maxRange");

        return currentTemperature >= minRange && currentTemperature <= maxRange;
    }
}
