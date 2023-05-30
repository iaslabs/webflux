package com.co.ias.webflux.infraestructure.driven_adapters.postgresR2DBC.config;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class DbSecret {

    private String username;
    private String password;
    private String host;
    private Integer port;
    private String database;
}
