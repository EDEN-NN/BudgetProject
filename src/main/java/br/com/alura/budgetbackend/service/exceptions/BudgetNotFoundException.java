package br.com.alura.budgetbackend.service.exceptions;

import java.io.Serializable;

public class BudgetNotFoundException extends RuntimeException implements Serializable {
    public BudgetNotFoundException(String msg) {
        super(msg);
    }
}
