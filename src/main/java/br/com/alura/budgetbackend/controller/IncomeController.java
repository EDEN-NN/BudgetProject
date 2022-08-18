package br.com.alura.budgetbackend.controller;

import br.com.alura.budgetbackend.model.Income;
import br.com.alura.budgetbackend.service.IncomeService;
import br.com.alura.budgetbackend.service.exceptions.EmptyIncomeException;
import br.com.alura.budgetbackend.service.exceptions.IncomeNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api")
public class IncomeController {

    @Autowired
    private IncomeService incomeService;

    @PostMapping("/receitas")
    public ResponseEntity<Income> saveIncome(@Valid @RequestBody Income income) {
               income = incomeService.saveIncome(income);
               URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(income.getId()).toUri();
               return ResponseEntity.created(uri).build();
    }

    @GetMapping("/receitas")
    public ResponseEntity<List<Income>> getAllIncomes() {
        List<Income> incomes = incomeService.findAll();
        return ResponseEntity.ok().body(incomes);
    }

    @GetMapping("/receitas/{id}")
    public ResponseEntity<Income> getById(@Valid @PathVariable Long id) {
       Income income = incomeService.findById(id);
       return ResponseEntity.ok().body(income);
    }

    @PutMapping("/receitas/{id}")
    public ResponseEntity<Income> updateIncome(@Valid @PathVariable Long id, @Valid @RequestBody Income income) {
        income = incomeService.updateIncome(id, income);
        return ResponseEntity.ok().body(income);
    }

    @DeleteMapping("/receitas/{id}")
    public ResponseEntity<Void> deleteIncome(@Valid @PathVariable Long id) {
        incomeService.deleteIncome(id);
        return ResponseEntity.accepted().build();
    }

}
