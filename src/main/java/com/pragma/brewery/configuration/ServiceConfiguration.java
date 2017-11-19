package com.pragma.brewery.configuration;

import com.pragma.brewery.dao.Drinks;
import com.pragma.brewery.dao.TemperatureDataDao;
import com.pragma.brewery.service.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.annotation.Resource;
import java.util.List;

import static com.google.common.collect.Lists.newArrayList;

/**
 * Created by ranjeethpt on 19/11/17.
 *
 * @author ranjeethpt
 */
@Configuration
public class ServiceConfiguration {

    public static final String TEMP_MONITOR_BEAN = "temperatureMonitorService";

    @Resource
    private List<Drinks> drinks;

    @Bean
    public List<Drinks> drinks() {
        drinks = newArrayList();
        drinks.add(new Drinks("Pilsner", 4d, 6d));
        drinks.add(new Drinks("IPA", 5d, 6d));
        drinks.add(new Drinks("Lager", 4d, 7d));
        drinks.add(new Drinks("Stout", 6d, 8d));
        drinks.add(new Drinks("Wheat beer", 3d, 5d));
        drinks.add(new Drinks("Pale Ale", 4d, 6d));

        return drinks;
    }

    @Bean(name = TEMP_MONITOR_BEAN)
    public TemperatureMonitorService temperatureMonitorService(ReadTemperatureService readTemperatureService, TemperatureIdentifierService temperatureIdentifierService,
                                                               NotifyMessageService notifyMessageService) {
        return new TemperatureMonitorServiceImpl(readTemperatureService, temperatureIdentifierService, notifyMessageService, drinks);
    }

    @Bean
    public ReadTemperatureService readTemperatureService(TemperatureDataDao temperatureDataDao) {
        return new ReadTemperatureServiceImpl(temperatureDataDao, drinks);
    }

    @Bean
    public TemperatureIdentifierService temperatureIdentifierService() {
        return new TemperatureIdentifierServiceImpl();
    }

    @Bean
    public NotifyMessageService notifyMessageService() {
        return new NotifyMessageServiceImpl();
    }

}
