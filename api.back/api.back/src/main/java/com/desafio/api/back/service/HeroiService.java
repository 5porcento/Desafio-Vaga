package com.desafio.api.back.service;

import com.desafio.api.back.entity.Heroi;
import com.desafio.api.back.entity.Superpoderes;
import com.desafio.api.back.entity.DTO.HeroiRequest;
import com.desafio.api.back.entity.mapper.NewMapper;
import com.desafio.api.back.repository.HeroiRepository;
import com.desafio.api.back.repository.SuperPoderesRepository;
import jakarta.transaction.Transactional;

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


    @Transactional
    public Heroi atualizarHeroi(Integer id, HeroiRequest request) {
        Heroi heroiExistente = heroiRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Herói com ID " + id + " não encontrado."));

        Optional<Heroi> heroiComMesmoNome = heroiRepository.findByNomeHeroi(request.nomeHeroi());

        if (heroiComMesmoNome.isPresent() && !heroiComMesmoNome.get().getId().equals(id)) {
            throw new RuntimeException("Já existe um herói com o nomeHeroi: " + request.nomeHeroi());
        }

        heroiExistente.setNome(request.nome());
        heroiExistente.setNomeHeroi(request.nomeHeroi());
        heroiExistente.setDataNascimento(request.dataNascimento());
        heroiExistente.setAltura(request.altura());
        heroiExistente.setPeso(request.peso());

        if (request.superpoderes() != null) {
            List<Superpoderes> superpoderes = request.superpoderes().stream()
                    .map(idPoder -> superPoderesRepository.findById(idPoder)
                            .orElseThrow(() -> new RuntimeException("Superpoder com ID " + idPoder + " não encontrado.")))
                    .toList();

            heroiExistente.setSuperpoderes(new ArrayList<>(superpoderes));
        }

        return heroiRepository.save(heroiExistente);
    }



}
