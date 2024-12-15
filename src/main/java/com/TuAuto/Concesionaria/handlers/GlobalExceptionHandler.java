package com.TuAuto.Concesionaria.handlers;

import com.TuAuto.Concesionaria.exceptions.ResourceNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Object> handleValidationExceptions(MethodArgumentNotValidException ex) {
        Map<String, String> errorMessages = new HashMap<>();
        ex.getBindingResult().getFieldErrors().forEach(fieldError ->
                errorMessages.put(fieldError.getField(), fieldError.getDefaultMessage())
        );
        return new ResponseEntity<>(errorMessages, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<Object> handleResourceNotFoundException(ResourceNotFoundException ex) {
        // Crear una respuesta de error con el mensaje de la excepción
        Map<String, String> errorResponse = new HashMap<>();
        errorResponse.put("error", "Resource Not Found");
        errorResponse.put("message", ex.getMessage());

        // Retornar un 404 con el mensaje de error
        return new ResponseEntity<>(errorResponse, HttpStatus.NOT_FOUND);
    }

}
