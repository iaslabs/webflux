package com.co.ias.webflux.infraestructure.entry_points.user;

import com.co.ias.webflux.domain.model.User;
import com.co.ias.webflux.infraestructure.driven_adapters.postgresR2DBC.UserRepository;
import com.co.ias.webflux.infraestructure.entry_points.ApiRoutes;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.WebFluxTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.reactive.server.WebTestClient;
import reactor.core.publisher.Mono;

import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.when;

@ExtendWith(SpringExtension.class)
@WebFluxTest({ApiRoutes.class, UserHandler.class})
class UserHandlerTest {

    @MockBean
    private UserRepository userRepository;

    @Autowired
    private WebTestClient webTestClient;

    @Test
    @DisplayName("Create user success")
    void createUser() {
        //Arrange
        User user = User
                .builder()
                .id(1)
                .name("Prueba")
                .email("correo")
                .build();
        given(userRepository.save(user)).willReturn(Mono.just(user));

        //Act & Assert
        webTestClient
                .post()
                .uri("/users")
                .contentType(MediaType.APPLICATION_JSON)
                .bodyValue(user)
                .exchange()
                .expectStatus()
                .isCreated()
                .expectBody(User.class)
                .isEqualTo(user);
    }

    @Test
    @DisplayName("Create user invalido")
    void createInvalidUser() {

    }

    @Test
    void queryUsers() {
    }

    @Test
    @DisplayName("Query user by id success")
    void queryUserById() {
        //Arrange
        User user = User
                .builder()
                .id(1)
                .name("User")
                .email("uncorreo@correo")
                .build();
        when(userRepository
                     .findById(1))
                     .thenReturn(Mono.just(user));

        //Act & Assert
        webTestClient
                .get()
                .uri("/users/{id}", 1)
                .exchange()
                .expectStatus()
                .isOk()
                .expectBody(User.class)
                .isEqualTo(user);
    }
}