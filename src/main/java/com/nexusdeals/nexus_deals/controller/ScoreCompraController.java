package com.nexusdeals.nexus_deals.controller;

import com.nexusdeals.nexus_deals.service.ScoreCompraService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("/api/score-compra")
public class ScoreCompraController {

    @Autowired
    private ScoreCompraService scoreCompraService;

    @GetMapping("/oferta/{ofertaId}")
    public Map<String, Object> calcularScore(@PathVariable Long ofertaId) {
        return scoreCompraService.calcularScore(ofertaId);
    }
}