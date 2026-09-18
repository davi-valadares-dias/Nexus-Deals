package com.nexusdeals.nexus_deals.controller;


import com.nexusdeals.nexus_deals.model.Oferta;
import com.nexusdeals.nexus_deals.service.OfertaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/ofertas")
public class OfertaController {

    @Autowired
    private OfertaService ofertaService;

    @GetMapping
    public List<Oferta> listarTodas() {
        return ofertaService.listarTodas();
    }

    @GetMapping("/{id}")
    public Oferta buscarPorId(@PathVariable Long id) {
        return ofertaService.buscarPorId(id);
    }

    @GetMapping("/produto/{produtoId}")
    public List<Oferta> buscarPorProduto(@PathVariable Long produtoId) {
        return ofertaService.buscarPorProduto(produtoId);
    }

    @PostMapping
    public Oferta criar(@RequestBody Oferta oferta) {
        return ofertaService.salvar(oferta);
    }

    @PutMapping("/{id}")
    public Oferta atualizar(@PathVariable Long id, @RequestBody Oferta oferta) {
        oferta.setId(id);
        return ofertaService.salvar(oferta);
    }

    @DeleteMapping("/{id}")
    public void deletar(@PathVariable Long id) {
        ofertaService.deletar(id);
    }
}