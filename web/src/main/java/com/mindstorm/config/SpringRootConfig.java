package com.mindstorm.config;

/**
 * Created by ecandela on 23/08/2015.
 */
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan({ "com.mindstorm.service" })
public class SpringRootConfig {
}