package com.nexusdeals.nexus_deals.service;

import com.nexusdeals.nexus_deals.model.Oferta;
import com.nexusdeals.nexus_deals.repository.HistoricoPrecoRepository;
import com.nexusdeals.nexus_deals.repository.OfertaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

@Service
public class ScoreCompraService {

    @Autowired
    private OfertaRepository ofertaRepository;

    @Autowired
    private HistoricoPrecoRepository historicoPrecoRepository;

    public Map<String, Object> calcularScore(Long ofertaId) {
        Oferta oferta = ofertaRepository.findById(ofertaId).orElse(null);

        if (oferta == null) {
            Map<String, Object> erro = new HashMap<>();
            erro.put("erro", "Oferta nao encontrada");
            return erro;
        }

        double scorePreco = calcularScorePreco(oferta, ofertaId);
        double scoreFrete = calcularScoreFrete(oferta);
        double scoreReputacao = calcularScoreReputacao(oferta);

        double scoreTotal = (scorePreco * 0.5) + (scoreFrete * 0.2) + (scoreReputacao * 0.3);
        scoreTotal = Math.round(scoreTotal * 100.0) / 100.0;

        Map<String, Object> resultado = new HashMap<>();
        resultado.put("scoreTotal", scoreTotal);
        resultado.put("mensagem", gerarMensagem(scoreTotal));
        resultado.put("detalhamento", Map.of(
                "scorePreco", scorePreco,
                "scoreFrete", scoreFrete,
                "scoreReputacaoLoja", scoreReputacao
        ));

        return resultado;
    }

    private double calcularScorePreco(Oferta oferta, Long ofertaId) {
        Double precoMedio = historicoPrecoRepository.buscarPrecoMedio(ofertaId);

        if (precoMedio == null) {
            return 70.0;
        }

        double precoAtual = oferta.getPreco().doubleValue();
        double diferencaPercentual = ((precoAtual - precoMedio) / precoMedio) * 100;

        double score = 100 - (diferencaPercentual * 3);

        if (score > 100) score = 100;
        if (score < 0) score = 0;

        return Math.round(score * 100.0) / 100.0;
    }

    private double calcularScoreFrete(Oferta oferta) {
        BigDecimal frete = oferta.getFrete();

        if (frete == null || frete.compareTo(BigDecimal.ZERO) == 0) {
            return 100.0;
        }

        double razao = frete.doubleValue() / oferta.getPreco().doubleValue();

        if (razao < 0.02) return 80.0;
        if (razao < 0.05) return 60.0;
        return 40.0;
    }

    private double calcularScoreReputacao(Oferta oferta) {
        Double reputacao = oferta.getLoja().getReputacao();

        if (reputacao == null) {
            return 50.0;
        }

        return (reputacao / 5.0) * 100;
    }

    private String gerarMensagem(double scoreTotal) {
        if (scoreTotal >= 80) {
            return "Excelente oportunidade";
        } else if (scoreTotal >= 60) {
            return "Boa oportunidade";
        } else if (scoreTotal >= 40) {
            return "Oportunidade razoavel";
        } else {
            return "Nao recomendado no momento";
        }
    }
}