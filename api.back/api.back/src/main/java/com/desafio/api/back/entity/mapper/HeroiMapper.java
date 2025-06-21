package com.desafio.api.back.entity.mapper;


import com.desafio.api.back.entity.DTO.HeroiRequestDTO;
import com.desafio.api.back.entity.Heroi;
import org.springframework.stereotype.Component;

@Component
public class HeroiMapper {

    public Heroi map(HeroiRequestDTO heroiRequestDTO) {
        Heroi heroi = new Heroi();
        heroi.setId(heroiRequestDTO.getId());
        heroi.setNome(heroiRequestDTO.getNome());
        heroi.setNomeHeroi(heroiRequestDTO.getNomeHeroi());
        heroi.setDataNascimento(heroiRequestDTO.getDataNascimento());
        heroi.setPeso(heroiRequestDTO.getPeso());
        heroi.setAltura(heroiRequestDTO.getAltura());
        heroi.setSuperpoderes(heroiRequestDTO.getSuperpoderes());
        return heroi;
    }

    public HeroiRequestDTO map(Heroi heroi) {
        HeroiRequestDTO heroiRequestDTO = new HeroiRequestDTO();
        heroiRequestDTO.setId(heroi.getId());
        heroiRequestDTO.setNome(heroi.getNome());
        heroiRequestDTO.setNomeHeroi(heroi.getNomeHeroi());
        heroiRequestDTO.setDataNascimento(heroi.getDataNascimento());
        heroiRequestDTO.setPeso(heroi.getPeso());
        heroiRequestDTO.setAltura(heroi.getAltura());
        heroiRequestDTO.setSuperpoderes(heroi.getSuperpoderes());
        return heroiRequestDTO;
    }
}
