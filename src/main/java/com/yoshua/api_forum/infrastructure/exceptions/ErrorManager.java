package com.yoshua.api_forum.infrastructure.exceptions;

import jakarta.persistence.EntityNotFoundException;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.List;

@RestControllerAdvice
public class ErrorManager {

    @ExceptionHandler(EntityNotFoundException.class)
    public ResponseEntity<Void> exception404Handler() {
        return ResponseEntity.notFound().build();
    }
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<List<DataException>> exception400Handler(MethodArgumentNotValidException exception) {
        var exceptionList = exception.getFieldErrors().stream()
                .map(DataException::new)
                .toList();
        return ResponseEntity.badRequest().body(exceptionList);
    }

    public record DataException(String campo, String mensaje) {
        public DataException(FieldError error) {
            this(error.getField(), error.getDefaultMessage());
        }
    }
}

