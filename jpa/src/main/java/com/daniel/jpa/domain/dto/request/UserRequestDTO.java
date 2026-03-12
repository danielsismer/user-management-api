package com.daniel.jpa.domain.dto.request;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.br.CPF;

public record UserRequestDTO (

        @NotBlank
        @Length(min=2, max=255)
        String nome,

        @Email
        @NotBlank
        String email,

        @CPF
        @NotBlank
        String cpf
){

}
