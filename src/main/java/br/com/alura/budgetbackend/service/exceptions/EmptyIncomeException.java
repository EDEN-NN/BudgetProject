package br.com.alura.budgetbackend.service.exceptions;

import java.io.Serializable;

public class EmptyIncomeException extends RuntimeException implements Serializable {

    public EmptyIncomeException(String msg) {
        super(msg);
    }

}
