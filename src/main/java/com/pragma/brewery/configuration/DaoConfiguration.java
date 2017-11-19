package com.pragma.brewery.configuration;

import com.pragma.brewery.dao.TemperatureDataDao;
import com.pragma.brewery.dao.TemperatureDataDaoImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Created by ranjeethpt on 19/11/17.
 *
 * @author ranjeethpt
 */
@Configuration
public class DaoConfiguration {

    @Bean
    public TemperatureDataDao temperatureDataDao() {
        return new TemperatureDataDaoImpl();
    }
}

