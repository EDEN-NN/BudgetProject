package br.com.alura.budgetbackend.repository;


import br.com.alura.budgetbackend.model.Outgo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OutgoRepository extends JpaRepository<Outgo, Long> {

    List<Outgo> findByDescription(String description);

}
