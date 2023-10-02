package org.test.microservice.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.test.library.CalculateService;
import org.test.library.CalculateServiceImpl;
import org.test.library.MetricService;
import org.test.library.MetricServiceImpl;

@Configuration
public class ExternalLibraryConfiguration {

    @Bean
    public CalculateService calculateService() {
        return new CalculateServiceImpl();
    }

    @Bean
    public MetricService metricService() {
        return new MetricServiceImpl();
    }
}
