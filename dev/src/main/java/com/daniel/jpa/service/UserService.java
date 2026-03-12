package com.daniel.jpa.service;

import com.daniel.jpa.domain.dto.request.AdressRequestDTO;
import com.daniel.jpa.domain.dto.request.UserRequestDTO;
import com.daniel.jpa.domain.dto.response.UserResponseDTO;
import com.daniel.jpa.domain.model.Address;
import com.daniel.jpa.domain.model.User;
import com.daniel.jpa.exception.custom.ResourceNotFound;
import com.daniel.jpa.mapper.AddressMapper;
import com.daniel.jpa.mapper.UserMapper;
import com.daniel.jpa.repository.UserRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final UserMapper userMapper;
    private final AddressMapper adressMapper;

    public UserResponseDTO save(UserRequestDTO userRequestDTO){

        User user = userMapper.toEntity(userRequestDTO);

        return userMapper.toResponse(userRepository.save(user));

    }

    public UserResponseDTO findById(Long id) {

        User user = userRepository.findById(id)
                .orElseThrow( () -> new ResourceNotFound("User not found " + id));

        return userMapper.toResponse(user);
    }

    public void delete(Long id) {

        if (!userRepository.existsById(id)) {
            throw new ResourceNotFound("User not found " + id);
        }

        userRepository.deleteById(id);
    }

    public List<UserResponseDTO> findAll() {

        return userRepository.findAll()
                .stream()
                .map(userMapper::toResponse)
                .toList();

    }


    @Transactional
    public UserResponseDTO update(UserRequestDTO dto, Long id) {
        User user = userRepository.findById(id).orElseThrow();
        userMapper.updateEntityFromDto(dto, user);
        return userMapper.toResponse(userRepository.save(user));
    }

    @Transactional
    public UserResponseDTO addAddressToUser(Long userId, AdressRequestDTO addrDto) {

        User user = userRepository.findById(userId)
                .orElseThrow(() -> new ResourceNotFound("User not found " + userId));

        Address address = adressMapper.toEntity(addrDto);

        user.addAdress(address);

        User savedUser = userRepository.save(user);

        return userMapper.toResponse(savedUser);
    }
}
