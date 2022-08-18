package br.com.alura.budgetbackend.exception;

import br.com.alura.budgetbackend.service.exceptions.DuplicateBudgetException;
import br.com.alura.budgetbackend.service.exceptions.EmptyBudgetException;
import br.com.alura.budgetbackend.service.exceptions.BudgetNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import javax.servlet.http.HttpServletRequest;
import java.io.Serializable;
import java.time.Instant;

@ControllerAdvice
public class ResourceExceptionHandler extends RuntimeException implements Serializable {

    @ExceptionHandler(DuplicateBudgetException.class)
    public ResponseEntity<StandardException> duplicateIncome(DuplicateBudgetException e, HttpServletRequest req) {
        String error = e.getMessage();
        HttpStatus status = HttpStatus.BAD_REQUEST;
        StandardException err = new StandardException(Instant.now(), error, status.value(), req.getRequestURI(), e.getMessage());
        return ResponseEntity.status(status).body(err);
    }

    @ExceptionHandler(EmptyBudgetException.class)
    public ResponseEntity<StandardException> emptyIncome(EmptyBudgetException e, HttpServletRequest req) {
        String error = e.getMessage();
        HttpStatus status = HttpStatus.BAD_REQUEST;
        StandardException err = new StandardException(Instant.now(), error, status.value(), req.getRequestURI(), e.getMessage());
        return ResponseEntity.status(status).body(err);
    }

    @ExceptionHandler(BudgetNotFoundException.class)
    public ResponseEntity<StandardException> incomeNotFound(BudgetNotFoundException e, HttpServletRequest req) {
        String error = e.getMessage();
        HttpStatus status = HttpStatus.NOT_FOUND;
        StandardException err = new StandardException(Instant.now(), error, status.value(), req.getRequestURI(), e.getMessage());
        return ResponseEntity.status(status).body(err);
    }
}
