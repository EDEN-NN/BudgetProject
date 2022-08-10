package br.com.alura.budgetbackend.controller;

import br.com.alura.budgetbackend.model.Income;
import br.com.alura.budgetbackend.service.IncomeService;
import br.com.alura.budgetbackend.service.exceptions.EmptyIncomeException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;

@RestController
@RequestMapping("/api")
public class IncomeController {

    @Autowired
    private IncomeService incomeService;

    @PostMapping("/receitas")
    public ResponseEntity<Income> saveIncome(@Valid @RequestBody Income income) {
           try {
               income = incomeService.saveIncome(income);
               URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(income.getId()).toUri();
               return ResponseEntity.created(uri).build();
           } catch(HttpMessageNotReadableException e) {
               throw new EmptyIncomeException(e.getMessage());
           }
    }

}
