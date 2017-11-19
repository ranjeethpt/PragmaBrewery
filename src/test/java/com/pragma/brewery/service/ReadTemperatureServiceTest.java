package com.pragma.brewery.service;

import com.pragma.brewery.dao.Drinks;
import com.pragma.brewery.dao.TemperatureDataDao;
import com.pragma.brewery.model.TemperatureData;
import org.hamcrest.CoreMatchers;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import java.util.List;

import static com.google.common.collect.Lists.newArrayList;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

/**
 * Created by ranjeethpt on 19/11/17.
 *
 * @author ranjeethpt
 */
@RunWith(MockitoJUnitRunner.class)
public class ReadTemperatureServiceTest {

    private ReadTemperatureService readTemperatureService;

    @Mock
    private TemperatureDataDao temperatureDataDao;

    private TemperatureData grootTemperatureData;

    @Rule
    public ExpectedException expectedException = ExpectedException.none();

    private String tempId1 = "Groot!!";

    @Before
    public void setUp() {
        List<Drinks> drinks = newArrayList();
        drinks.add(new Drinks(tempId1, 1.23, 3.45));
        drinks.add(new Drinks("Rocket Racoon", 3.23, 4.45));

        readTemperatureService = new ReadTemperatureServiceImpl(temperatureDataDao, drinks);
        grootTemperatureData = new TemperatureData(tempId1, 123d);
        when(temperatureDataDao.getCurrentTemperature(tempId1)).thenReturn(grootTemperatureData);
    }

    /**
     * It should get the temperature from sensor based on the id.
     */
    @Test
    public void testReadCurrentTemperature() {
        assertThat(readTemperatureService.readCurrentTemperature(tempId1)).isNotNull().isEqualTo(grootTemperatureData);
    }

    /**
     * It should throw {@link TemperatureDataNotFoundException} when id is not present.
     */
    @Test
    public void testReadCurrentTemperatureDataNotFound() {
        expectedException.expect(TemperatureDataNotFoundException.class);
        expectedException.expectMessage(CoreMatchers.equalTo("Thanos is not present in our records. Available Drinks are Groot!!,Rocket Racoon."));
        readTemperatureService.readCurrentTemperature("Thanos");
    }

}