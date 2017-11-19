package com.pragma.brewery.configuration;

import com.pragma.brewery.service.ReadTemperatureService;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import static org.mockito.Mockito.mock;

/**
 * Created by ranjeethpt on 19/11/17.
 *
 * @author ranjeethpt
 */
@Configuration
@EnableAutoConfiguration
public class TestContextConfiguration {

    @Bean
    public ReadTemperatureService readTemperatureService() {
        return mock(ReadTemperatureService.class);
    }
}
