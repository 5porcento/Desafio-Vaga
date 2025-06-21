package com.desafio.api.back.controller;

import com.desafio.api.back.entity.DTO.HeroiRequestDTO;
import com.desafio.api.back.entity.Heroi;
import com.desafio.api.back.service.HeroiService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/heroi")
public class HeroiController {

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

    @PostMapping("/criar")
    public HeroiRequestDTO criar(@RequestBody HeroiRequestDTO heroiDTO) {
        return heroiService.cadastrarHeroi(heroiDTO);
    }

}
