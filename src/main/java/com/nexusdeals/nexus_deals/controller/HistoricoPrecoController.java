package com.nexusdeals.nexus_deals.controller;

import com.nexusdeals.nexus_deals.model.HistoricoPreco;
import com.nexusdeals.nexus_deals.service.HistoricoPrecoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/historico-precos")
public class HistoricoPrecoController {

    @Autowired
    private HistoricoPrecoService historicoPrecoService;

    @GetMapping("/oferta/{ofertaId}")
    public List<HistoricoPreco> buscarPorOferta(@PathVariable Long ofertaId) {
        return historicoPrecoService.buscarPorOferta(ofertaId);
    }

    @GetMapping("/oferta/{ofertaId}/estatisticas")
    public Map<String, Object> buscarEstatisticas(@PathVariable Long ofertaId){
        return historicoPrecoService.buscarEstatisticas(ofertaId);
    }

    @PostMapping
    public HistoricoPreco criar(@RequestBody HistoricoPreco historicoPreco) {
        return historicoPrecoService.salvar(historicoPreco);
    }
}