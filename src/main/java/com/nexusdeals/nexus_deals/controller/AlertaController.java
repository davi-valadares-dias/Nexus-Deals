package com.nexusdeals.nexus_deals.controller;

import com.nexusdeals.nexus_deals.model.Alerta;
import com.nexusdeals.nexus_deals.service.AlertaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/alertas")
public class AlertaController {

    @Autowired
    private AlertaService alertaService;

    @GetMapping
    public List<Alerta> listarTodos() {
        return alertaService.listarTodos();
    }

    @GetMapping("/oferta/{ofertaId}")
    public List<Alerta> buscarPorOferta(@PathVariable Long ofertaId) {
        return alertaService.buscarPorOferta(ofertaId);
    }

    @PostMapping
    public Alerta criar(@RequestBody Alerta alerta) {
        return alertaService.criar(alerta);
    }

    @PutMapping("/{id}/desativar")
    public void desativar(@PathVariable Long id) {
        alertaService.desativar(id);
    }

    @PostMapping("/verificar")
    public String verificar() {
        alertaService.verificarAlertas();
        return "Verificacao de alertas executada";
    }
}