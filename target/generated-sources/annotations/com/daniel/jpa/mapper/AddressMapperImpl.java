package com.daniel.jpa.mapper;

import com.daniel.jpa.domain.dto.request.AdressRequestDTO;
import com.daniel.jpa.domain.dto.response.AdressResponseDTO;
import com.daniel.jpa.domain.model.Address;
import com.daniel.jpa.domain.model.User;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2026-03-12T20:03:43-0300",
    comments = "version: 1.5.5.Final, compiler: javac, environment: Java 22 (Oracle Corporation)"
)
@Component
public class AddressMapperImpl implements AddressMapper {

    @Override
    public Address toEntity(AdressRequestDTO dto) {
        if ( dto == null ) {
            return null;
        }

        Address address = new Address();

        address.setCountry( dto.country() );
        address.setCity( dto.city() );
        address.setStreet( dto.street() );

        return address;
    }

    @Override
    public AdressResponseDTO toResponse(Address entity) {
        if ( entity == null ) {
            return null;
        }

        Long user = null;
        Long id = null;
        String street = null;

        user = entityUserId( entity );
        id = entity.getId();
        street = entity.getStreet();

        AdressResponseDTO adressResponseDTO = new AdressResponseDTO( id, street, user );

        return adressResponseDTO;
    }

    private Long entityUserId(Address address) {
        if ( address == null ) {
            return null;
        }
        User user = address.getUser();
        if ( user == null ) {
            return null;
        }
        Long id = user.getId();
        if ( id == null ) {
            return null;
        }
        return id;
    }
}
