package com.co.ias.webflux.infraestructure.driven_adapters.postgresR2DBC;

import com.co.ias.webflux.domain.model.User;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends ReactiveCrudRepository<User,Integer> {
}
