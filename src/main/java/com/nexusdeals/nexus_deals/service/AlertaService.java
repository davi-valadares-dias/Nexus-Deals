package com.nexusdeals.nexus_deals.service;

import com.nexusdeals.nexus_deals.model.Alerta;
import com.nexusdeals.nexus_deals.model.Oferta;
import com.nexusdeals.nexus_deals.repository.AlertaRepository;
import com.nexusdeals.nexus_deals.repository.OfertaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class AlertaService {

    @Autowired
    private AlertaRepository alertaRepository;

    @Autowired
    private OfertaRepository ofertaRepository;

    public List<Alerta> listarTodos() {
        return alertaRepository.findAll();
    }

    public List<Alerta> buscarPorOferta(Long ofertaId) {
        return alertaRepository.findByOfertaId(ofertaId);
    }

    public Alerta criar(Alerta alerta) {
        alerta.setAtivo(true);
        alerta.setDisparado(false);
        alerta.setDataCriacao(LocalDateTime.now());
        return alertaRepository.save(alerta);
    }

    public void desativar(Long id) {
        Alerta alerta = alertaRepository.findById(id).orElse(null);
        if (alerta != null) {
            alerta.setAtivo(false);
            alertaRepository.save(alerta);
        }
    }

    public void verificarAlertas() {
        List<Alerta> alertasAtivos = alertaRepository.findByAtivoTrue();

        for (Alerta alerta : alertasAtivos) {
            Oferta oferta = alerta.getOferta();

            if (oferta.getPreco().compareTo(alerta.getPrecoAlvo()) <= 0) {
                alerta.setDisparado(true);
                alerta.setDataDisparo(LocalDateTime.now());
                alertaRepository.save(alerta);
            }
        }
    }
}