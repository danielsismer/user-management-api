    package com.daniel.jpa.controller;

    import com.daniel.jpa.domain.dto.request.AdressRequestDTO;
    import com.daniel.jpa.domain.dto.request.UserRequestDTO;
    import com.daniel.jpa.domain.dto.response.UserResponseDTO;
    import com.daniel.jpa.service.UserService;
    import jakarta.validation.Valid;
    import lombok.Getter;
    import lombok.RequiredArgsConstructor;
    import org.apache.coyote.Response;
    import org.springframework.http.HttpStatus;
    import org.springframework.http.ResponseEntity;
    import org.springframework.web.bind.annotation.*;

    import java.util.List;

    @RequiredArgsConstructor
    @RestController
    @RequestMapping("/users")
    public class UserController {

        private final UserService userService;

        @PostMapping
        public ResponseEntity<UserResponseDTO> save(@Valid @RequestBody UserRequestDTO userRequestDTO){
            return ResponseEntity.status(HttpStatus.CREATED)
                    .body(userService.save(userRequestDTO));
        }

        @GetMapping("/{id}")
        public ResponseEntity<UserResponseDTO> findById(@PathVariable Long id){
            return ResponseEntity.status(HttpStatus.OK)
                    .body(userService.findById(id));
        }

        @GetMapping
        public ResponseEntity<List<UserResponseDTO>> findAll(){
            return ResponseEntity.status(HttpStatus.OK)
                    .body(userService.findAll());
        }

        @DeleteMapping("/{id}")
        public ResponseEntity<Void> delete(@PathVariable Long id){
            userService.delete(id);
            return ResponseEntity
                    .noContent()
                    .build();
        }

        @PutMapping("/{id}")
        public ResponseEntity<UserResponseDTO> update(@Valid @RequestBody UserRequestDTO userRequestDTO, @PathVariable Long id){
            return ResponseEntity.status(HttpStatus.OK)
                    .body(userService.update(userRequestDTO, id));
        }


        @PostMapping("/{id}/addresses")
        public ResponseEntity<UserResponseDTO> addAddress(
                @PathVariable Long id,
                @Valid @RequestBody AdressRequestDTO addressRequestDTO) {

            return ResponseEntity.status(HttpStatus.CREATED)
                    .body(userService.addAddressToUser(id, addressRequestDTO));
        }

    }
