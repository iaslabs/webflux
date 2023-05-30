package com.co.ias.webflux.infraestructure.driven_adapters.postgresR2DBC.config;

import java.util.Objects;

import io.r2dbc.postgresql.PostgresqlConnectionConfiguration;
import io.r2dbc.postgresql.PostgresqlConnectionFactory;
import io.r2dbc.spi.ConnectionFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.data.r2dbc.config.AbstractR2dbcConfiguration;
import org.springframework.data.r2dbc.repository.config.EnableR2dbcRepositories;

@Configuration
@EnableR2dbcRepositories
public class DbConfig extends AbstractR2dbcConfiguration {

    @Bean
    public DbSecret secret(Environment environment) {
        return new DbSecret(environment.getProperty("spring.datasource.username"),
                            environment.getProperty("spring.datasource.password"),
                            environment.getProperty("spring.datasource.host"),
                            Integer.parseInt(environment.getProperty("spring.datasource.port")),
                            environment.getProperty("spring.datasource.databasename"));
    }

    @Override
    @Bean
    public ConnectionFactory connectionFactory() {
        return new PostgresqlConnectionFactory(
                PostgresqlConnectionConfiguration
                        .builder()
                        .host("localhost")
                        .port(5432)
                        .username("postgres")
                        .password("admin")
                        .database("reactive")
                        .build());
    }
}
