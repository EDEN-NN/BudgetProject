package br.com.alura.budgetbackend.controller;

import br.com.alura.budgetbackend.model.Income;
import br.com.alura.budgetbackend.service.IncomeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.lang.Nullable;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.util.List;

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
    public ResponseEntity<List<Income>> getAllIncomes(@RequestParam(required = false) String descricao) {
        List<Income> incomes = descricao == null ? incomeService.findAll() : incomeService.findByDescription(descricao);
        return ResponseEntity.ok().body(incomes);

//        if(description.isEmpty()) {
//            List<Income> incomes = incomeService.findAll();
//            return ResponseEntity.ok().body(incomes);
//        }
//        List<Income> incomes = incomeService.findByDescription(description);
//        return ResponseEntity.ok().body(incomes);
    }

    @GetMapping("/receitas/{year}/{month}")
    public ResponseEntity<List<Income>> getAllByDate(@PathVariable String year, @PathVariable String month) {
        return ResponseEntity.ok().body(incomeService.findByDate(year, month));
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
