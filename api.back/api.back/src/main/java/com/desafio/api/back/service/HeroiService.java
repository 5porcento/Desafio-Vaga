package com.desafio.api.back.service;

import com.desafio.api.back.entity.DTO.HeroiRequestDTO;
import com.desafio.api.back.entity.DTO.HeroiResponse;
import com.desafio.api.back.entity.Heroi;
import com.desafio.api.back.entity.Superpoderes;
import com.desafio.api.back.entity.DTO.HeroiRequest;
import com.desafio.api.back.entity.mapper.NewMapper;
import com.desafio.api.back.repository.HeroiRepository;
import com.desafio.api.back.repository.SuperPoderesRepository;
import jakarta.transaction.Transactional;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

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

    public Optional<Heroi> findById (Integer id) {
        return heroiRepository.findById(id);
    }

//    public Optional<Heroi> update (Integer id, Heroi heroiUpdate) {
//        Optional<Heroi> optHeroi = heroiRepository.findById(id);
//        if (optHeroi.isPresent()) {
//            List<Superpoderes> superpoderes = this.listarSuperpoderes(heroiUpdate.getSuperpoderes());
//
//            Heroi heroi = optHeroi.get();
//            heroi.setNome(heroiUpdate.getNome());
//            heroi.setNomeHeroi(heroiUpdate.getNomeHeroi());
//            heroi.setPeso(heroiUpdate.getPeso());
//            heroi.setAltura(heroiUpdate.getAltura());
//            heroi.getSuperpoderes().clear();
//            heroi.getSuperpoderes().addAll(superpoderes);
//            heroiRepository.save(heroi);
//            return Optional.of(heroi);
//
//        }
//        return Optional.empty();
//    }


    @Transactional
    public Heroi atualizarHeroi(Integer id, HeroiRequest dto) {
        Heroi heroiExistente = heroiRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Herói não encontrado com o ID: " + id));

        // Atualizar os campos básicos
        heroiExistente.setNome(dto.nome());
        heroiExistente.setNomeHeroi(dto.nomeHeroi());
        heroiExistente.setDataNascimento(dto.dataNascimento());
        heroiExistente.setAltura(dto.altura());
        heroiExistente.setPeso(dto.peso());

        // Atualizar os superpoderes
        if (dto.superpoderes() != null) {
            List<Superpoderes> superpoderes = superPoderesRepository.findAllById(dto.superpoderes());
            heroiExistente.setSuperpoderes(superpoderes);
        }

        return heroiRepository.save(heroiExistente);
    }


//    private List<Superpoderes> listarSuperpoderes (List<Superpoderes> superpoderes) {
//        List<Superpoderes> listaSuperpoderes = new ArrayList<>();
//        listaSuperpoderes.forEach(superpoder -> superPoderesRepository.findById(superpoder.getId()).ifPresent(listaSuperpoderes::add));
//        return listaSuperpoderes;
//    }


}
