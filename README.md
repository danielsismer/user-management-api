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

---

## 📁 Project Structure

```
src/
└── main/
    └── java/
        └── com/example/usermanagement/
            ├── controller/       # REST Controllers
            ├── dto/              # Data Transfer Objects
            ├── entity/           # JPA Entities
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

## ⚙️ How to Build

This project uses `mapstruct-processor` to generate mapper implementations during compile time. Ensure you have the build lifecycle configured correctly:

```bash
mvn clean compile
```

To run the application:

```bash
mvn spring-boot:run
```

> Make sure your MySQL instance is running and the connection properties are set in `application.properties` before starting.

---

## 💡 Lessons Learned

- **Decoupling:** Entities are protected from the Controller layer via DTOs.
- **Performance:** Use of JPA/Hibernate best practices to handle `LAZY` loading.
