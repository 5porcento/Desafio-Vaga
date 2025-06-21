package com.desafio.api.back.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "Herois")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Heroi {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(unique = true)
    private String nome;
    private String nomeHeroi;

    @ManyToMany
    @JoinTable(
            name = "HeroisSuperpoderes", // Nome da tabela de junção conforme o diagrama
            joinColumns = @JoinColumn(name = "HeroId"), // Coluna que referencia o Heroi (conforme diagrama)
            inverseJoinColumns = @JoinColumn(name = "SuperpoderId") // Coluna que referencia o Superpoder (conforme diagrama)
    )
    private List<Superpoderes> superpoderes;
    private LocalDate dataNascimento;
    private float altura;
    private float peso;
}
