package com.desafio.api.back.repository;

import com.desafio.api.back.entity.Superpoderes;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SuperPoderesRepository extends JpaRepository <Superpoderes, Integer> {
}
