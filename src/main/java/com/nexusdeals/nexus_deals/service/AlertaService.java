package com.nexusdeals.nexus_deals.service;

import com.nexusdeals.nexus_deals.model.Alerta;
import com.nexusdeals.nexus_deals.model.Oferta;
import com.nexusdeals.nexus_deals.model.Usuario;
import com.nexusdeals.nexus_deals.repository.AlertaRepository;
import com.nexusdeals.nexus_deals.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class AlertaService {

    @Autowired
    private AlertaRepository alertaRepository;

    @Autowired
    private UsuarioRepository usuarioRepository;

    public List<Alerta> listarDoUsuario(String emailUsuario) {
        return alertaRepository.findByUsuarioEmail(emailUsuario);
    }

    public Alerta criar(Alerta alerta, String emailUsuario) {
        Usuario usuario = usuarioRepository.findByEmail(emailUsuario)
                .orElseThrow(() -> new IllegalArgumentException("Usuario nao encontrado"));

        alerta.setId(null);
        alerta.setUsuario(usuario);
        alerta.setAtivo(true);
        alerta.setDisparado(false);
        alerta.setDataCriacao(LocalDateTime.now());

        return alertaRepository.save(alerta);
    }

    public void desativar(Long id, String emailUsuario) {
        Alerta alerta = alertaRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Alerta nao encontrado"));

        if (alerta.getUsuario() == null || !alerta.getUsuario().getEmail().equals(emailUsuario)) {
            throw new IllegalArgumentException("Alerta nao encontrado");
        }

        alerta.setAtivo(false);
        alertaRepository.save(alerta);
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

    @Scheduled(fixedRate = 60000)
    public void verificarAlertasAutomaticamente() {
        System.out.println("Executando verificacao automatica de alertas...");
        verificarAlertas();
    }
}