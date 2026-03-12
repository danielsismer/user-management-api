package com.daniel.jpa.domain.dto.request;

import com.daniel.jpa.domain.model.User;

public record AdressRequestDTO(
        String country,
        String city,
        String street
) {
}
