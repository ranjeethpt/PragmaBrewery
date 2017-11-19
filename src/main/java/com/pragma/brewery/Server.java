package com.pragma.brewery;

import com.pragma.brewery.service.TemperatureMonitorService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

import static com.pragma.brewery.configuration.ServiceConfiguration.TEMP_MONITOR_BEAN;

/**
 * Created by ranjeethpt on 19/11/17.
 *
 * @author ranjeethpt
 */
@Slf4j
@SpringBootApplication
public class Server {

    public static void main(String[] args) {
        ConfigurableApplicationContext configurableApplicationContext = SpringApplication.run(Server.class, args);
        TemperatureMonitorService temperatureMonitorService = configurableApplicationContext.getBean(TEMP_MONITOR_BEAN, TemperatureMonitorService.class);
        while (true) {
            log.info("#####Monitoring#####");
            temperatureMonitorService.monitor();
            try {
                Thread.sleep(5000);
            } catch (InterruptedException e) {
                log.error("Sleep interrupted", e);
            }
        }
    }
}
