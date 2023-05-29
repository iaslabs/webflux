package com.co.ias.webflux.infraestructure.entry_points;

import com.co.ias.webflux.infraestructure.entry_points.user.UserHandler;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.RouterFunctions;
import org.springframework.web.reactive.function.server.ServerResponse;

import static org.springframework.web.reactive.function.server.RequestPredicates.*;

@Configuration
public class ApiRoutes {

    @Value("{PATH_BASE}")
    private String pathBase;

    @Bean
    public RouterFunction<ServerResponse> userFunctionalEndpoints(UserHandler handler) {
        String param = "/{id}";
        return RouterFunctions
                .route(POST(pathBase).and(accept(MediaType.APPLICATION_JSON)), handler::createUser)
                .andRoute(GET(pathBase).and(accept(MediaType.APPLICATION_JSON)), handler::queryUsers)
                .andRoute(GET(pathBase.concat(param)).and(accept(MediaType.APPLICATION_JSON)), handler::queryUserById)
                .andRoute(PUT(pathBase.concat(param)).and(accept(MediaType.APPLICATION_JSON)), handler::updateUser)
                .andRoute(DELETE(pathBase.concat(param)).and(accept(MediaType.APPLICATION_JSON)),
                          handler::deleteUser);

    }

}
