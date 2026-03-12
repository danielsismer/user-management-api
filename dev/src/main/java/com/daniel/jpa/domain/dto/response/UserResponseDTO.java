package com.daniel.jpa.domain.dto.response;

public record UserResponseDTO (
        Long id,
        String nome,
        String email,
        String cpf
){
}
