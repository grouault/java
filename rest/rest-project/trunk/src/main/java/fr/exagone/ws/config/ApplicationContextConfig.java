package fr.exagone.ws.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.ImportResource;

/**
 * 
 */
@Configuration
@ComponentScan(basePackages = "fr.exagone.ws.ressources")
@ImportResource("classpath:ws-context.xml")
public class ApplicationContextConfig {
}