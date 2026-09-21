package com.nexusdeals.nexus_deals.service;

import com.nexusdeals.nexus_deals.model.HistoricoPreco;
import com.nexusdeals.nexus_deals.model.Oferta;
import com.nexusdeals.nexus_deals.repository.HistoricoPrecoRepository;
import com.nexusdeals.nexus_deals.repository.OfertaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class HistoricoPrecoService {
    @Autowired
    private HistoricoPrecoRepository historicoPrecoRepository;

    public List<HistoricoPreco> buscarPorOferta(Long ofertaId) {
        return historicoPrecoRepository.findByOfertaIdOrderByDataColetaDesc(ofertaId);
    }
    public HistoricoPreco salvar(HistoricoPreco historicoPreco){
        return historicoPrecoRepository.save(historicoPreco);
    }
    public Map<String, Object> buscarEstatisticas(Long ofertaId){
        BigDecimal menorPreco = historicoPrecoRepository.buscarMenorPreco(ofertaId);
        BigDecimal maiorPreco = historicoPrecoRepository.buscarMaiorPreco(ofertaId);
        Double precoMedio = historicoPrecoRepository.buscarPrecoMedio(ofertaId);

        Map<String, Object> estatisticas = new HashMap<>();
        estatisticas.put("menorPreco", menorPreco);
        estatisticas.put("maiorPreco", maiorPreco);
        estatisticas.put("precoMedio", precoMedio);

        return estatisticas;
    }
}
