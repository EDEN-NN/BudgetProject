package br.com.alura.budgetbackend.model;

import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
@Data
public class Income {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @NotNull
    @Column(name = "description")
    private String description;

    @NotNull
    @Column(name = "value")
    private Double value;

    @NotNull
    @Column(name = "data")
    private String data;



}
