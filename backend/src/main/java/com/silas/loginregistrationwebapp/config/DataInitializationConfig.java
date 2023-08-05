package com.silas.loginregistrationwebapp.config;
import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.jdbc.datasource.init.DataSourceInitializer;
import org.springframework.jdbc.datasource.init.ResourceDatabasePopulator;

@Configuration
public class DataInitializationConfig {

    @Value("${spring.datasource.url}")
    private String datasourceUrl;

    @Value("${spring.datasource.username}")
    private String datasourceUsername;

    @Value("${spring.datasource.password}")
    private String datasourcePassword;

    @Bean
    public DataSourceInitializer dataSourceInitializer(DataSource dataSource) {
        ResourceDatabasePopulator populator = new ResourceDatabasePopulator(new ClassPathResource("data.sql"));
        DataSourceInitializer initializer = new DataSourceInitializer();
        initializer.setDataSource(dataSource);
        initializer.setDatabasePopulator(populator);
        return initializer;
    }

    @Bean
    public DataSource dataSource() {
        return DataSourceBuilder.create()
                .url(datasourceUrl)
                .username(datasourceUsername)
                .password(datasourcePassword)
                .build();
    }
}
