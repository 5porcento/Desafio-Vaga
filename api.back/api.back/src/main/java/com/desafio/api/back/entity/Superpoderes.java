package com.desafio.api.back.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Table(name = "Superpoderes")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Superpoderes {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String superpoder;
    private String descricao;

    @ManyToMany(mappedBy = "superpoderes")
    private List<Heroi> herois;
}
