package com.daniel.jpa.exception;

import com.daniel.jpa.domain.dto.response.ErrorResponseDTO;

import com.daniel.jpa.exception.custom.ResourceNotFound;
import org.hibernate.exception.ConstraintViolationException;
import org.springframework.dao.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ErrorResponseDTO> captureMethodArgument(MethodArgumentNotValidException ex){

        Map<String, String> errors = new HashMap<>();

        ex.getBindingResult()
                .getFieldErrors()
                .forEach(
                        error -> {
                            errors.put(error.getField(), error.getDefaultMessage());
                        }
                );

        return buildResponse(HttpStatus.BAD_REQUEST, "Dados inválidos: verifique os campos obrigatórios.", errors);
    }

    @ExceptionHandler(ConstraintViolationException.class)
    public ResponseEntity<ErrorResponseDTO> captureConstraintViolation(ConstraintViolationException ex){
        return buildResponse(HttpStatus.BAD_REQUEST,"Erro de preenchimento: verifique os dados obrigatórios.", null);
    }

    @ExceptionHandler(InvalidDataAccessApiUsageException.class)
    public ResponseEntity<ErrorResponseDTO> captureInvalidDataAccess(InvalidDataAccessApiUsageException ex){
        return buildResponse(HttpStatus.BAD_REQUEST, "Requisição inválida: não foi possível processar a ação.", null);
    }

    @ExceptionHandler(OptimisticLockingFailureException.class)
    public ResponseEntity<ErrorResponseDTO> captureOptimisticLocking(OptimisticLockingFailureException ex){
        return buildResponse(HttpStatus.CONFLICT, "Este registro foi atualizado por outro usuário. Recarregue a página.", null);
    }

    @ExceptionHandler(DataIntegrityViolationException.class)
    public ResponseEntity<ErrorResponseDTO> captureDataIntegrity(DataIntegrityViolationException ex){
        return buildResponse(HttpStatus.CONFLICT, "Conflito de dados: este registro possui dependências ou já existe.", null);
    }

    @ExceptionHandler(DataAccessResourceFailureException.class)
    public ResponseEntity<ErrorResponseDTO> captureDataAccessResource(DataAccessResourceFailureException ex){
        return buildResponse(HttpStatus.SERVICE_UNAVAILABLE, "Serviço temporariamente indisponível. Tente novamente mais tarde.", null);
    }

    @ExceptionHandler(ResourceNotFound.class)
    public ResponseEntity<ErrorResponseDTO> captureResourceNotFound(ResourceNotFound ex){
        return buildResponse(HttpStatus.NOT_FOUND, ex.getMessage(), null);
    }

    @ExceptionHandler(EmptyResultDataAccessException.class)
    public ResponseEntity<ErrorResponseDTO> captureNotFound(EmptyResultDataAccessException ex) {
        return buildResponse(HttpStatus.NOT_FOUND, "Registro não encontrado para exclusão.", null);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorResponseDTO> captureException(Exception ex){
        return buildResponse(HttpStatus.INTERNAL_SERVER_ERROR, "Erro não esperado", null);
    }

    private ResponseEntity<ErrorResponseDTO> buildResponse(HttpStatus  status, String message, Map<String, String> errors){

        ErrorResponseDTO errorResponseDTO = new ErrorResponseDTO(
                LocalDateTime.now(),
                status.value(),
                message, errors
        );

        return ResponseEntity.status(status)
                .body(errorResponseDTO);
    }
}