package com.nexusdeals.nexus_deals.controller;

import com.nexusdeals.nexus_deals.service.ComparacaoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

@RestController
public class ComparacaoController {

    @Autowired
    private ComparacaoService comparacaoService;

    @GetMapping("/api/comparacao")
    public Map<String, Object> comparar(@RequestParam List<Long> produtoIds) {
        return comparacaoService.compararProdutos(produtoIds);
    }
}