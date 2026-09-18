package com.nexusdeals.nexus_deals.controller;

import com.nexusdeals.nexus_deals.model.Loja;
import com.nexusdeals.nexus_deals.service.LojaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/lojas")
public class LojaController {

    @Autowired
    private LojaService lojaService;

    @GetMapping
    public List<Loja> listarTodas() {
        return lojaService.listarTodas();
    }

    @GetMapping("/{id}")
    public Loja buscarPorId(@PathVariable Long id) {
        return lojaService.buscarPorId(id);
    }

    @PostMapping
    public Loja criar(@RequestBody Loja loja) {
        return lojaService.salvar(loja);
    }

    @PutMapping("/{id}")
    public Loja atualizar(@PathVariable Long id, @RequestBody Loja loja) {
        loja.setId(id);
        return lojaService.salvar(loja);
    }

    @DeleteMapping("/{id}")
    public void deletar(@PathVariable Long id) {
        lojaService.deletar(id);
    }
}