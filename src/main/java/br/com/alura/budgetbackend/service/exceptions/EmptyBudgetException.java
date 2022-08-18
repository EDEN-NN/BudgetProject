package br.com.alura.budgetbackend.service.exceptions;

import java.io.Serializable;

public class EmptyBudgetException extends RuntimeException implements Serializable {

    public EmptyBudgetException(String msg) {
        super(msg);
    }

}
