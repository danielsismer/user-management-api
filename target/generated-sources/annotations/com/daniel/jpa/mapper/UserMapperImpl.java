package com.daniel.jpa.mapper;

import com.daniel.jpa.domain.dto.request.UserRequestDTO;
import com.daniel.jpa.domain.dto.response.UserResponseDTO;
import com.daniel.jpa.domain.model.User;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2026-03-12T20:03:43-0300",
    comments = "version: 1.5.5.Final, compiler: javac, environment: Java 22 (Oracle Corporation)"
)
@Component
public class UserMapperImpl implements UserMapper {

    @Override
    public User toEntity(UserRequestDTO dto) {
        if ( dto == null ) {
            return null;
        }

        User user = new User();

        user.setNome( dto.nome() );
        user.setEmail( dto.email() );
        user.setCpf( dto.cpf() );

        return user;
    }

    @Override
    public UserResponseDTO toResponse(User entity) {
        if ( entity == null ) {
            return null;
        }

        Long id = null;
        String nome = null;
        String email = null;
        String cpf = null;

        id = entity.getId();
        nome = entity.getNome();
        email = entity.getEmail();
        cpf = entity.getCpf();

        UserResponseDTO userResponseDTO = new UserResponseDTO( id, nome, email, cpf );

        return userResponseDTO;
    }

    @Override
    public void updateEntityFromDto(UserRequestDTO dto, User entity) {
        if ( dto == null ) {
            return;
        }

        if ( dto.nome() != null ) {
            entity.setNome( dto.nome() );
        }
        if ( dto.email() != null ) {
            entity.setEmail( dto.email() );
        }
        if ( dto.cpf() != null ) {
            entity.setCpf( dto.cpf() );
        }
    }
}
