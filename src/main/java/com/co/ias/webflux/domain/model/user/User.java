package com.co.ias.webflux.domain.model.user;

import lombok.Builder;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;

@Data
@Builder
@Table("users")
public class User {

    private Name name;
    @Id
    private Identification id;
    private Email email;
}
