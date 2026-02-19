package me.kopz.watch.exception;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.Instant;
import java.util.List;

@RestControllerAdvice
public class GlobalExceptionHandler {

  @ExceptionHandler(NotFoundException.class)
  public ResponseEntity<ErrorApi> notFoundException(NotFoundException ex, HttpServletRequest req) {
    return ResponseEntity.status(HttpStatus.NOT_FOUND).body(
        new ErrorApi(Instant.now(), 404, "Not Found", ex.getMessage(), req.getRequestURI(), List.of())
    );
  }

  @ExceptionHandler(IllegalArgumentException.class)
  public ResponseEntity<ErrorApi> handleInvalidRequest(IllegalArgumentException ex, HttpServletRequest req) {
    return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(
        new ErrorApi(Instant.now(), 400, "Invalid request", ex.getMessage(), req.getRequestURI(), List.of())
    );
  }

  @ExceptionHandler(MethodArgumentNotValidException.class)
  public ResponseEntity<ErrorApi> handleValidation(MethodArgumentNotValidException ex, HttpServletRequest req) {
    List<ErrorApi.PathError> paths = ex.getBindingResult().getFieldErrors().stream()
        .map(fe -> new ErrorApi.PathError(fe.getField(), message(fe)))
        .toList();

    return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(
        new ErrorApi(Instant.now(), 400, "Invalid request", "Validation error", req.getRequestURI(), paths)
    );
  }

  private String message(FieldError fe) {
    return fe.getDefaultMessage() != null ? fe.getDefaultMessage() : "invalid";
  }


}
