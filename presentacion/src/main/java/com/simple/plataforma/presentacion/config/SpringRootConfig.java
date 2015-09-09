package com.simple.plataforma.presentacion.config;

/**
 * Created by ecandela on 23/08/2015.
 */
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan({"com.simple.plataforma.presentacion.service"})
public class SpringRootConfig {
}