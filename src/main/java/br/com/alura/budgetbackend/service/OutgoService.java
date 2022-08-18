package br.com.alura.budgetbackend.service;


import br.com.alura.budgetbackend.model.Category;
import br.com.alura.budgetbackend.model.Income;
import br.com.alura.budgetbackend.model.Outgo;
import br.com.alura.budgetbackend.repository.OutgoRepository;
import br.com.alura.budgetbackend.service.exceptions.BudgetNotFoundException;
import br.com.alura.budgetbackend.service.exceptions.DuplicateBudgetException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class OutgoService {

    @Autowired
    private OutgoRepository outgoRepository;

    public Outgo saveOutgo(Outgo outgo) {
        List<Outgo> list = outgoRepository.findByDescription(outgo.getDescription());
        list.forEach((obj) -> {
            if(obj.getDescription().equals(outgo.getDescription()) && obj.getData().equals(outgo.getData())) {
                throw new DuplicateBudgetException("Despesa já existente!");
            }
        });
        if(outgo.getCategory() == null) {
            outgo.setCategory(Category.OUTRAS);
        }
        return outgoRepository.save(outgo);
    }

    public List<Outgo> findAll() {
        return outgoRepository.findAll();
    }

    public List<Outgo> findByDescription(String description) {
        return outgoRepository.findByDescription(description);
    }

    public Outgo findById(Long id) {
        Optional<Outgo> outgo = outgoRepository.findById(id);
        if(outgo.isEmpty()) {
            throw new BudgetNotFoundException("Despesa não encontrada!");
        }
        return outgo.get();
    }

    public Outgo updateOutgo(Long id, Outgo outgo) {
        Optional<Outgo> outgo1 = outgoRepository.findById(id);
        if(outgo1.isEmpty()) {
            throw new BudgetNotFoundException("Despesa não existe!");
        }
        List<Outgo> list = outgoRepository.findAll();
        list.forEach((obj) -> {
            if(obj.getDescription().equals(outgo.getDescription()) && obj.getData().equals(outgo.getData())) {
                throw new DuplicateBudgetException("Despesa já existente!");
            }
        });
        if(outgo1.get().getCategory() == null) {
            if(outgo.getCategory() == null) {
                outgo.setCategory(Category.OUTRAS);
            }
        } else {
            outgo.setCategory(outgo1.get().getCategory());
        }
        outgo.setId(id);
        return outgoRepository.save(outgo);
    }

    public void deleteOutgo(Long id) {
        Outgo outgo = this.findById(id);
        if(outgo == null) {
            throw new BudgetNotFoundException("Despesa não existe!");
        }
        outgoRepository.delete(outgo);
    }


}
