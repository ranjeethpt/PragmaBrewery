package com.pragma.brewery.dao;

import com.pragma.brewery.model.TemperatureData;
import lombok.Data;

/**
 * Created by ranjeethpt on 19/11/17.
 *
 * @author ranjeethpt
 */
@Data
public class Drinks extends TemperatureData {
    private Double minRange;
    private Double maxRange;

    public Drinks(String id, Double minRange, Double maxRange) {
        super(id, null);
        this.minRange = minRange;
        this.maxRange = maxRange;
    }
}
