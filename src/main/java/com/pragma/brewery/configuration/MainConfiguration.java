package com.pragma.brewery.configuration;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.context.annotation.PropertySource;

/**
 * Created by ranjeethpt on 19/11/17.
 *
 * @author ranjeethpt
 */
@Configuration
@Import({
        WebConfiguration.class,
        ServiceConfiguration.class,
        DaoConfiguration.class
})
@PropertySource("classpath:configuration-default.properties")
public class MainConfiguration {
}
