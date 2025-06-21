package com.desafio.api.back.entity.DTO;

import com.desafio.api.back.entity.Superpoderes;
import lombok.*;

import java.time.LocalDate;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class HeroiRequestDTO {
        private int id;
        private String nome;
        private String nomeHeroi;
        private List<Superpoderes> superpoderes;
        private LocalDate dataNascimento;
        private float altura;
        private float peso;
}
