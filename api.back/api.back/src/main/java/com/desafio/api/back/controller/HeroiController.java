package com.desafio.api.back.controller;

import com.desafio.api.back.entity.DTO.HeroiRequest;
import com.desafio.api.back.entity.DTO.HeroiRequestDTO;
import com.desafio.api.back.entity.DTO.HeroiResponse;
import com.desafio.api.back.entity.Heroi;
import com.desafio.api.back.entity.Superpoderes;
import com.desafio.api.back.entity.mapper.NewMapper;
import com.desafio.api.back.repository.HeroiRepository;
import com.desafio.api.back.repository.SuperPoderesRepository;
import com.desafio.api.back.service.HeroiService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/heroi")
public class HeroiController {

    @Autowired
    private HeroiRepository heroiRepository;
    @Autowired
    private SuperPoderesRepository superPoderesRepository;

    private final HeroiService heroiService;
    public HeroiController(HeroiService heroiService) {
        this.heroiService = heroiService;
    }

    @GetMapping
    public ResponseEntity<?> findAll() {
        List<Heroi> herois = heroiService.listarTodosHerois();
        if (herois.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("Nenhum super-herói cadastrado no sistema.");
        }
        return ResponseEntity.ok(herois);
    }

    @PostMapping
    public HeroiResponse save(@RequestBody HeroiRequest dto) {
        Heroi heroi = heroiService.salvarHeroi(dto);
        return NewMapper.toHeroiResponse(heroi);
    }

    //TODO: adicionar tratamento de erro
    @GetMapping("/pegar/{id}")
    public ResponseEntity<HeroiResponse> findHeroiById(@PathVariable Integer id) {
        return heroiService.findById(id)
                .map(heroi -> ResponseEntity.ok(NewMapper.toHeroiResponse(heroi)))
                .orElse(ResponseEntity.status(HttpStatus.NOT_FOUND).build());
    }

    @PutMapping("/{id}")
    public ResponseEntity<HeroiResponse> atualizarHeroi(@PathVariable Integer id, @RequestBody HeroiRequest dto) {
        Heroi heroiAtualizado = heroiService.atualizarHeroi(id, dto);
        return ResponseEntity.ok(NewMapper.toHeroiResponse(heroiAtualizado));
    }





}
