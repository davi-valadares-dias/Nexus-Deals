package com.nexusdeals.nexus_deals.service;

import com.nexusdeals.nexus_deals.model.HistoricoPreco;
import com.nexusdeals.nexus_deals.model.Oferta;
import com.nexusdeals.nexus_deals.repository.HistoricoPrecoRepository;
import com.nexusdeals.nexus_deals.repository.OfertaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class HistoricoPrecoService {
    @Autowired
    private HistoricoPrecoRepository historicoPrecoRepository;

    public List<HistoricoPreco> listarbuscarPorOferta(Long ofertaId) {
        return historicoPrecoRepository.findByOfertaIdOrderByDataColetaDesc(ofertaId);
    }
    public HistoricoPreco salvar(HistoricoPreco historicoPreco){
        return historicoPrecoRepository.save(historicoPreco);
    }
}
