package br.com.alura.budgetbackend.service;


import br.com.alura.budgetbackend.model.Income;
import br.com.alura.budgetbackend.repository.IncomeRepository;
import br.com.alura.budgetbackend.service.exceptions.DuplicateBudgetException;
import br.com.alura.budgetbackend.service.exceptions.BudgetNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class IncomeService {

    @Autowired
    private IncomeRepository incomeRepository;

    public Income saveIncome(Income income) {
        List<Income> list = incomeRepository.findByDescription(income.getDescription());
        list.forEach((obj) -> {
            if(obj.getDescription().equals(income.getDescription()) && obj.getData().equals(income.getData())) {
                throw new DuplicateBudgetException("Receita já existente!");
            }
        });
        return incomeRepository.save(income);
    }

    public List<Income> findAll() {
        return incomeRepository.findAll();
    }

    public Income findById(Long id) {
        Optional<Income> income = incomeRepository.findById(id);
        if(income.isEmpty()) {
            throw new BudgetNotFoundException("Receita não encontrada!");
        }
        return income.get();
    }

    public Income updateIncome(Long id, Income income) {
        List<Income> list = incomeRepository.findAll();
        list.forEach((obj) -> {
            if(obj.getDescription().equals(income.getDescription()) && obj.getData().equals(income.getData())) {
                throw new DuplicateBudgetException("Receita já existente!");
            }
        });
        income.setId(id);
        return incomeRepository.save(income);
    }

    public void deleteIncome(Long id) {
        Income income = this.findById(id);
        if(income == null) {
            throw new BudgetNotFoundException("Receita não existe!");
        }
        incomeRepository.delete(income);
    }


}
