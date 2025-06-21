package com.desafio.api.back.service;

import com.desafio.api.back.entity.DTO.HeroiRequestDTO;
import com.desafio.api.back.entity.Heroi;
import com.desafio.api.back.entity.mapper.HeroiMapper;
import com.desafio.api.back.repository.HeroiRepository;
import com.desafio.api.back.repository.SuperPoderesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class HeroiService {

    private final HeroiRepository heroiRepository;
    private HeroiMapper heroiMapper;
    private final SuperPoderesRepository superPoderesRepository;

    public HeroiService(HeroiRepository heroiRepository, HeroiMapper heroiMapper, SuperPoderesRepository superPoderesRepository) {
        this.heroiRepository = heroiRepository;
        this.heroiMapper = heroiMapper;
        this.superPoderesRepository = superPoderesRepository;
    }

    public List<Heroi> listarTodosHerois() {
        return heroiRepository.findAll();
    }

    public HeroiRequestDTO cadastrarHeroi(HeroiRequestDTO heroiDTO) {
        Heroi heroiCriado = new HeroiMapper().map(heroiDTO);
        heroiCriado = heroiRepository.save(heroiCriado);
        return heroiMapper.map(heroiCriado);
    }


}
