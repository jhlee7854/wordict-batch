package kr.pe.jady.store.batch.config.spring.app;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

/**
 * Created by jhlee7854 on 2016. 11. 1..
 */
@Configuration
@ComponentScan(basePackages = {"kr.pe.jady.store.batch"},
        includeFilters = @ComponentScan.Filter(value = {Service.class, Repository.class, Component.class}),
        excludeFilters = @ComponentScan.Filter(value = {Controller.class}))
public class AppConfig {
    @Bean
    public static PropertySourcesPlaceholderConfigurer configurer() {
        PropertySourcesPlaceholderConfigurer configurer = new PropertySourcesPlaceholderConfigurer();
        configurer.setIgnoreResourceNotFound(false);
        configurer.setIgnoreUnresolvablePlaceholders(false);
        return configurer;
    }
}
