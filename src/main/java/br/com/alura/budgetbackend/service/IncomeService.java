package br.com.alura.budgetbackend.service;


import br.com.alura.budgetbackend.model.Income;
import br.com.alura.budgetbackend.repository.IncomeRepository;
import br.com.alura.budgetbackend.service.exceptions.DuplicateIncomeException;
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
                throw new DuplicateIncomeException("Receita já existente!");
            }
        });
        return incomeRepository.save(income);
    }


}
