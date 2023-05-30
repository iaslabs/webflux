package com.co.ias.webflux.infraestructure.entry_points.user;

import com.co.ias.webflux.domain.model.user.User;
import com.co.ias.webflux.infraestructure.driven_adapters.postgresR2DBC.IUserDBORepository;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;

@Component
@AllArgsConstructor
public class UserHandler {

    private final IUserDBORepository IUserDBORepository;

    public Mono<ServerResponse> createUser(ServerRequest serverRequest) {
        return serverRequest
                .bodyToMono(User.class)
                .flatMap(IUserDBORepository::save)
                .flatMap(savedUser -> ServerResponse
                        .status(HttpStatus.CREATED)
                        .bodyValue(savedUser))
                .onErrorResume(exception -> ServerResponse
                        .unprocessableEntity()
                        .bodyValue("Error al crear usuario."));

    }

    public Mono<ServerResponse> queryUsers(ServerRequest serverRequest) {
        return IUserDBORepository
                .findAll()
                .collectList()
                .flatMap(users -> {
                    if (!users.isEmpty()) {
                        return ServerResponse
                                .ok()
                                .bodyValue(users);
                    } else {
                        return ServerResponse
                                .noContent()
                                .build();
                    }
                })
                .switchIfEmpty(ServerResponse
                                       .noContent()
                                       .build());
    }

    public Mono<ServerResponse> queryUserById(ServerRequest serverRequest) {
        return IUserDBORepository
                .findById(Integer.valueOf(serverRequest.pathVariable("id")))
                .flatMap(user -> ServerResponse
                        .ok()
                        .bodyValue(user))
                .switchIfEmpty(ServerResponse
                                       .status(HttpStatus.NO_CONTENT)
                                       .bodyValue("Usuario no encontrado"));
    }

    public Mono<ServerResponse> updateUser(ServerRequest serverRequest) {
        return IUserDBORepository
                .findById(Integer.valueOf(serverRequest.pathVariable("id")))
                .flatMap(userInDatabase -> serverRequest
                        .bodyToMono(User.class)
                        .flatMap(userRequest -> {
                            User updatedUser = User
                                    .builder()
                                    .id(userInDatabase.getId())
                                    .name(userRequest.getName())
                                    .email(userRequest.getEmail())
                                    .build();
                            return ServerResponse
                                    .ok()
                                    .body(IUserDBORepository.save(updatedUser), User.class);
                        }))
                .switchIfEmpty(ServerResponse
                                       .status(HttpStatus.NOT_FOUND)
                                       .bodyValue("Usuarion no existe " +
                                                  "en el sistema."));
    }

    public Mono<ServerResponse> deleteUser(ServerRequest serverRequest) {
        return null;
    }
}
