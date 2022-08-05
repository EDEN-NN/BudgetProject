package br.com.alura.budgetbackend.model;

import lombok.Data;

import javax.persistence.*;

@Entity
@Data
public class Outgo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "description")
    private String description;

    @Column(name = "value")
    private Double value;

    @Column(name = "data")
    private String data;

    

}
