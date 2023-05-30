package com.co.ias.webflux.infraestructure.driven_adapters.postgresR2DBC;

import com.co.ias.webflux.domain.model.gateways.IUserRepository;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public class UserDBORepositoryAdapter implements IUserRepository {

    private final IUserDBORepository repository;
}
