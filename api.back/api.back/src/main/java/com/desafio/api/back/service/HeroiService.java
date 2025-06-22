package com.desafio.api.back.service;

import com.desafio.api.back.entity.Heroi;
import com.desafio.api.back.entity.Superpoderes;
import com.desafio.api.back.entity.DTO.HeroiRequest;
import com.desafio.api.back.entity.mapper.NewMapper;
import com.desafio.api.back.repository.HeroiRepository;
import com.desafio.api.back.repository.SuperPoderesRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class HeroiService {

    private final HeroiRepository heroiRepository;
    private final SuperPoderesRepository superPoderesRepository;

    public HeroiService(HeroiRepository heroiRepository, SuperPoderesRepository superPoderesRepository) {
        this.heroiRepository = heroiRepository;
        this.superPoderesRepository = superPoderesRepository;
    }

    public List<Heroi> listarTodosHerois() {
        return heroiRepository.findAll();
    }

    public Heroi salvarHeroi(HeroiRequest dto) {
        // Buscar os superpoderes pelo ID antes de salvar (para evitar DetachedEntityException)
        List<Superpoderes> superpoderes = superPoderesRepository.findAllById(dto.superpoderes());

        // Mapear o DTO para a entidade Heroi, agora com os superpoderes já gerenciados
        Heroi heroi = NewMapper.toHeroi(dto, superpoderes);

        // Salvar o heroi
        return heroiRepository.save(heroi);
    }
}
