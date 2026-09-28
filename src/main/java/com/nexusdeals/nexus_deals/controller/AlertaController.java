package com.nexusdeals.nexus_deals.controller;

import com.nexusdeals.nexus_deals.model.Alerta;
import com.nexusdeals.nexus_deals.service.AlertaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/alertas")
public class AlertaController {

    @Autowired
    private AlertaService alertaService;

    @GetMapping
    public List<Alerta> meusAlertas(Authentication authentication) {
        return alertaService.listarDoUsuario(authentication.getName());
    }

    @PostMapping
    public ResponseEntity<?> criar(@RequestBody Alerta alerta, Authentication authentication) {
        try {
            Alerta salvo = alertaService.criar(alerta, authentication.getName());
            return ResponseEntity.status(HttpStatus.CREATED).body(salvo);
        } catch (IllegalArgumentException e) {
            Map<String, String> erro = new HashMap<>();
            erro.put("erro", e.getMessage());
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(erro);
        }
    }

    @PutMapping("/{id}/desativar")
    public ResponseEntity<?> desativar(@PathVariable Long id, Authentication authentication) {
        try {
            alertaService.desativar(id, authentication.getName());
            return ResponseEntity.ok().build();
        } catch (IllegalArgumentException e) {
            Map<String, String> erro = new HashMap<>();
            erro.put("erro", e.getMessage());
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(erro);
        }
    }

    @PostMapping("/verificar")
    public String verificar() {
        alertaService.verificarAlertas();
        return "Verificacao de alertas executada";
    }
}