package br.com.alura.budgetbackend.service.exceptions;

import java.io.Serializable;

public class IncomeNotFoundException extends RuntimeException implements Serializable {
    public IncomeNotFoundException(String msg) {
        super(msg);
    }
}
