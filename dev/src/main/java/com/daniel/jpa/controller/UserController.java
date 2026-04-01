    package com.daniel.jpa.controller;

    import com.daniel.jpa.domain.dto.request.AdressRequestDTO;
    import com.daniel.jpa.domain.dto.request.UserRequestDTO;
    import com.daniel.jpa.domain.dto.response.UserResponseDTO;
    import com.daniel.jpa.service.UserService;
    import jakarta.validation.Valid;
    import lombok.RequiredArgsConstructor;
    import org.springframework.data.domain.Page;
    import org.springframework.data.domain.Pageable;
    import org.springframework.data.domain.Sort;
    import org.springframework.data.web.PageableDefault;
    import org.springframework.http.HttpStatus;
    import org.springframework.http.ResponseEntity;
    import org.springframework.web.bind.annotation.*;

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

    /**
     * MÉTODO ATUALIZADO:
     * Agora recebe parâmetros de paginação e devolve um objeto Page.
     */
        @GetMapping
        public ResponseEntity<Page<UserResponseDTO>> findAll(
                @PageableDefault(page = 0, size = 10, sort = "id", direction = Sort.Direction.ASC) Pageable pageable){
            return ResponseEntity.status(HttpStatus.OK)
                .body(userService.findAll(pageable));
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