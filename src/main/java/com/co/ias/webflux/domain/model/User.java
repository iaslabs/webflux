package com.co.ias.webflux.domain.model;

import lombok.Builder;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;

@Data
@Builder
@Table("users")
public class User {

    private String name;
    @Id
    private Integer id;
    private String email;
}
