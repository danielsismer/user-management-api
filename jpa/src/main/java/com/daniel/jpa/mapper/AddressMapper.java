package com.daniel.jpa.mapper;

import com.daniel.jpa.domain.dto.request.AdressRequestDTO;
import com.daniel.jpa.domain.dto.response.AdressResponseDTO;
import com.daniel.jpa.domain.model.Address;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface AddressMapper {

    Address toEntity(AdressRequestDTO dto);

    @Mapping(source = "user.id", target = "user")
    AdressResponseDTO toResponse(Address entity);
}