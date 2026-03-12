# User Management Service

A robust RESTful API built with **Spring Boot**, **JPA/Hibernate**, and **MapStruct**. Implements clean architecture principles with DTO-to-Entity mapping, bidirectional relationship management, and optimized data persistence.

---

## 🚀 Overview

This project provides a complete solution for managing users and their respective addresses. It follows professional development standards, ensuring clear separation of concerns, secure data handling, and clean API design.

---

## 🛠️ Tech Stack

| Technology | Version |
|---|---|
| Java | 21 |
| Spring Boot | 4.0.3 |
| Spring Data JPA | — |
| MapStruct | — |
| MySQL | — |
| Lombok | — |

---

## 📋 Key Features

- **RESTful Architecture:** Clear sub-resource handling (e.g., `/users/{id}/addresses`).
- **Efficient Mapping:** Automated DTO handling with MapStruct to prevent manual errors.
- **Relationship Integrity:** Bidirectional synchronization for Parent-Child entities (User-Address) with `CascadeType.ALL`.
- **Security & Cleanliness:** Use of DTOs to prevent circular references and unwanted exposure of internal entities.
- **Circular Reference Handling:** Smart mapping to return IDs instead of full objects in JSON responses.
- **Input Validation:** Bean Validation (`@Valid`, `@NotBlank`, `@Email`, etc.) applied on all request DTOs, rejecting invalid payloads before they reach the service layer.
- **Centralized Error Handling:** `@RestControllerAdvice` returning structured, consistent error responses across all endpoints.

---

## 📁 Project Structure

```
src/
└── main/
    └── java/
        └── com/example/usermanagement/
            ├── controller/       # REST Controllers
            ├── dto/              # Data Transfer Objects (request & response)
            ├── entity/           # JPA Entities
            ├── exception/        # Custom exceptions & GlobalExceptionHandler
            ├── mapper/           # MapStruct Mappers
            ├── repository/       # Spring Data Repositories
            └── service/          # Business Logic
```

---

## 📋 Main Endpoints

| Method | Endpoint | Description |
|---|---|---|
| `POST` | `/users` | Create a new user |
| `GET` | `/users/{id}` | Get user details |
| `POST` | `/users/{id}/addresses` | Add an address to an existing user |
| `PUT` | `/users/{id}` | Update user profile |
| `DELETE` | `/users/{id}` | Remove a user |

---

## ✅ Input Validation

All request DTOs are annotated with Bean Validation constraints. Invalid requests are rejected before reaching the service layer, returning a `400 Bad Request` with a descriptive message.

Example — `UserRequestDTO`:

```java
public class UserRequestDTO {

    @NotBlank(message = "Name is required")
    private String name;

    @NotBlank(message = "Email is required")
    @Email(message = "Invalid email format")
    private String email;

    @NotBlank(message = "Password is required")
    @Size(min = 6, message = "Password must be at least 6 characters")
    private String password;
}
```

Controllers use `@Valid` to trigger validation automatically:

```java
@PostMapping
public ResponseEntity<UserResponseDTO> create(@RequestBody @Valid UserRequestDTO dto) {
    return ResponseEntity.status(HttpStatus.CREATED).body(service.create(dto));
}
```

---

## 🚨 Error Handling

All exceptions are handled centrally by a `@RestControllerAdvice`, ensuring every error returns a clean, consistent JSON response — no stack traces exposed to the client.

### Error response format

```json
{
  "timestamp": "2026-03-12T14:30:00",
  "status": 404,
  "error": "Not Found",
  "message": "User with id 42 not found"
}
```

### Handled scenarios

| Scenario | HTTP Status |
|---|---|
| Resource not found | `404 Not Found` |
| Validation failure (`@Valid`) | `400 Bad Request` |
| Duplicate / constraint violation | `409 Conflict` |
| Unexpected server error | `500 Internal Server Error` |

### Example — `GlobalExceptionHandler`

```java
@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<ErrorResponseDTO> handleNotFound(ResourceNotFoundException ex) {
        ErrorResponseDTO error = new ErrorResponseDTO(
            LocalDateTime.now(),
            HttpStatus.NOT_FOUND.value(),
            "Not Found",
            ex.getMessage()
        );
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(error);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ErrorResponseDTO> handleValidation(MethodArgumentNotValidException ex) {
        String message = ex.getBindingResult().getFieldErrors().stream()
            .map(e -> e.getField() + ": " + e.getDefaultMessage())
            .collect(Collectors.joining(", "));

        ErrorResponseDTO error = new ErrorResponseDTO(
            LocalDateTime.now(),
            HttpStatus.BAD_REQUEST.value(),
            "Validation Failed",
            message
        );
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(error);
    }
}
```

---

## ⚙️ How to Build

This project uses `mapstruct-processor` to generate mapper implementations during compile time. Ensure you have the build lifecycle configured correctly:

```bash
mvn clean compile
```

To run the application:

```bash
mvn spring-boot:run
```

To run the tests:

```bash
mvn test
```

> Make sure your MySQL instance is running and the connection properties are set in `application.properties` before starting.

---

## 💡 Lessons Learned

- **Decoupling:** Entities are protected from the Controller layer via DTOs.
- **Performance:** Use of JPA/Hibernate best practices to handle `LAZY` loading.
- **Validation at the boundary:** Validating input at the DTO level keeps the service layer clean and focused on business logic.
- **Fail fast, fail clearly:** Centralized exception handling ensures the API always communicates errors in a predictable, client-friendly format.
