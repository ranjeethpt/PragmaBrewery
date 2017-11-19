package com.pragma.brewery.service;

import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * Created by ranjeethpt on 19/11/17.
 *
 * @author ranjeethpt
 */
public class TemperatureIdentifierServiceTest {
    private TemperatureIdentifierService temperatureIdentifierService;

    public TemperatureIdentifierServiceTest() {
        this.temperatureIdentifierService = new TemperatureIdentifierServiceImpl();
    }

    /**
     * It should return true when input temperature is between the range.
     */
    @Test
    public void testIsTemperatureInRangeTrue() {
        assertThat(temperatureIdentifierService.isTemperatureInRange(12.45, 12.40, 13.00)).isTrue();
        assertThat(temperatureIdentifierService.isTemperatureInRange(12.45, 12.45, 13.00)).isTrue();
        assertThat(temperatureIdentifierService.isTemperatureInRange(13.00, 12.40, 13.00)).isTrue();
    }

    /**
     * It should return false when input temperature is outside the range.
     */
    @Test
    public void testIsTemperatureInRangeFalse() {
        assertThat(temperatureIdentifierService.isTemperatureInRange(0.00, 12.40, 13.00)).isFalse();
        assertThat(temperatureIdentifierService.isTemperatureInRange(12.44, 12.45, 13.00)).isFalse();
        assertThat(temperatureIdentifierService.isTemperatureInRange(13.01, 12.40, 13.00)).isFalse();
    }


    /**
     * It should throw exception when ranges are incorrect.
     */
    @Test(expected = IllegalArgumentException.class)
    public void testIsOutsideTemperatureIncorrectRange() {
        temperatureIdentifierService.isTemperatureInRange(0.00, 2.40, 1.00);
    }


}