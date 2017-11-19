package com.pragma.brewery.configuration;

import com.pragma.brewery.web.controller.TemperatureController;
import com.pragma.brewery.service.ReadTemperatureService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Created by ranjeethpt on 19/11/17.
 *
 * @author ranjeethpt
 */
@Configuration
public class WebConfiguration {

    @Bean
    public TemperatureController temperatureController(ReadTemperatureService readTemperatureService) {
        return new TemperatureController(readTemperatureService);
    }
}
