package br.com.alura.budgetbackend.service.exceptions;

import java.io.Serializable;

public class DuplicateBudgetException extends RuntimeException implements Serializable {

    public DuplicateBudgetException(String msg) {
        super(msg);
    }

}
