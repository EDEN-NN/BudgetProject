package br.com.alura.budgetbackend.exception;

import br.com.alura.budgetbackend.service.exceptions.DuplicateIncomeException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import javax.servlet.http.HttpServletRequest;
import java.io.Serializable;
import java.time.Instant;

@ControllerAdvice
public class ResourceExceptionHandler extends RuntimeException implements Serializable {

    @ExceptionHandler(DuplicateIncomeException.class)
    public ResponseEntity<StandardException> duplicateIncome(DuplicateIncomeException e, HttpServletRequest req) {
        String error = e.getMessage();
        HttpStatus status = HttpStatus.BAD_REQUEST;
        StandardException err = new StandardException(Instant.now(), error, status.value(), req.getRequestURI(), e.getMessage());
        return ResponseEntity.status(status).body(err);
    }


}
