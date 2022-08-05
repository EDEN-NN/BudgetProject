package br.com.alura.budgetbackend.repository;

import br.com.alura.budgetbackend.model.Income;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface IncomeRepository extends JpaRepository<Income, Long> {

    List<Income> findByDescription(String description);

}
