package com.daniel.jpa.mapper;

import com.daniel.jpa.domain.dto.request.UserRequestDTO;
import com.daniel.jpa.domain.dto.response.UserResponseDTO;
import com.daniel.jpa.domain.model.User;
import org.mapstruct.BeanMapping;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;

@Mapper(componentModel = "spring", uses = {AddressMapper.class})
public interface UserMapper {

    User toEntity(UserRequestDTO dto);

    UserResponseDTO toResponse(User entity);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    void updateEntityFromDto(UserRequestDTO dto, @MappingTarget User entity);
}