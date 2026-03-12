package com.daniel.jpa.domain.dto.response;

import com.daniel.jpa.domain.model.User;

public record AdressResponseDTO (
        Long id,
        String street,
        Long user
){
}
