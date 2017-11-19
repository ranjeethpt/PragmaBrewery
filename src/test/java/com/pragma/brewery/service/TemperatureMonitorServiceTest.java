package com.pragma.brewery.service;

import com.pragma.brewery.dao.Drinks;
import com.pragma.brewery.model.TemperatureData;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import java.util.List;

import static com.google.common.collect.Lists.newArrayList;
import static org.mockito.Matchers.any;
import static org.mockito.Matchers.eq;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

/**
 * Created by ranjeethpt on 19/11/17.
 *
 * @author ranjeethpt
 */
@RunWith(MockitoJUnitRunner.class)
public class TemperatureMonitorServiceTest {

    private TemperatureMonitorService temperatureMonitorService;

    @Mock
    private NotifyMessageService notifyMessageService;

    @Mock
    private TemperatureIdentifierService temperatureIdentifierService;

    @Mock
    private ReadTemperatureService readTemperatureService;

    private String tempId1 = "Groot!!";
    private TemperatureData grootTemperatureData1;
    private Double tempId1CurrentTemperature = 2.45;

    private String tempId2 = "Rocket Racoon";
    private TemperatureData grootTemperatureData2;
    private Double tempId2CurrentTemperature = 5.45;

    private String tempId3 = "Thanos";
    private TemperatureData grootTemperatureData3;
    private Double tempId3CurrentTemperature = 15.45;


    @Before
    public void setUp() {
        List<Drinks> drinks = newArrayList();
        drinks.add(new Drinks(tempId1, 1.23, 3.45));
        drinks.add(new Drinks(tempId2, 3.23, 4.45));
        drinks.add(new Drinks(tempId3, 3.23, 5.45));

        grootTemperatureData1 = new TemperatureData(tempId1, tempId1CurrentTemperature);
        grootTemperatureData2 = new TemperatureData(tempId2, tempId2CurrentTemperature);
        grootTemperatureData3 = new TemperatureData(tempId2, tempId3CurrentTemperature);

        temperatureMonitorService = new TemperatureMonitorServiceImpl(readTemperatureService, temperatureIdentifierService, notifyMessageService, drinks);

    }

    /**
     * It should monitor all configured Drinks and check if the temperature is in range if not then notify.
     */
    @Test
    public void testMonitor() {
        when(readTemperatureService.readCurrentTemperature(tempId1)).thenReturn(grootTemperatureData1);
        when(readTemperatureService.readCurrentTemperature(tempId2)).thenReturn(grootTemperatureData2);
        when(readTemperatureService.readCurrentTemperature(tempId3)).thenReturn(grootTemperatureData3);

        when(temperatureIdentifierService.isTemperatureInRange(eq(tempId1CurrentTemperature), any(), any())).thenReturn(true);
        when(temperatureIdentifierService.isTemperatureInRange(eq(tempId2CurrentTemperature), any(), any())).thenReturn(false);
        when(temperatureIdentifierService.isTemperatureInRange(eq(tempId3CurrentTemperature), any(), any())).thenReturn(false);

        temperatureMonitorService.monitor();

        verify(notifyMessageService).notify(grootTemperatureData2, 3.23, 4.45);
        verify(notifyMessageService).notify(grootTemperatureData3, 3.23, 5.45);

    }

}