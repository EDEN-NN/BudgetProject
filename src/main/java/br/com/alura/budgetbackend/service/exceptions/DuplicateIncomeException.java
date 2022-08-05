package br.com.alura.budgetbackend.service.exceptions;

import java.io.Serializable;

public class DuplicateIncomeException extends RuntimeException implements Serializable {

    public DuplicateIncomeException(String msg) {
        super(msg);
    }

}
