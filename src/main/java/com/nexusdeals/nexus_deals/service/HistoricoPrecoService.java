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

    public Map<String, Object> buscarEstatisticas(Long ofertaId) {
        BigDecimal menorPreco = historicoPrecoRepository.buscarMenorPreco(ofertaId);
        BigDecimal maiorPreco = historicoPrecoRepository.buscarMaiorPreco(ofertaId);
        Double precoMedio = historicoPrecoRepository.buscarPrecoMedio(ofertaId);

        Map<String, Object> estatisticas = new HashMap<>();
        estatisticas.put("menorPreco", menorPreco);
        estatisticas.put("maiorPreco", maiorPreco);
        estatisticas.put("precoMedio", precoMedio);

        return estatisticas;
    }

    public Map<String, Object> compararComHistorico(Long ofertaId, BigDecimal precoAtual) {
        BigDecimal menorPreco = historicoPrecoRepository.buscarMenorPreco(ofertaId);
        BigDecimal maiorPreco = historicoPrecoRepository.buscarMaiorPreco(ofertaId);
        Double precoMedio = historicoPrecoRepository.buscarPrecoMedio(ofertaId);

        Map<String, Object> resultado = new HashMap<>();
        resultado.put("precoAtual", precoAtual);
        resultado.put("menorPreco", menorPreco);
        resultado.put("maiorPreco", maiorPreco);
        resultado.put("precoMedio", precoMedio);

        if (precoMedio != null) {
            double diferencaPercentual = ((precoAtual.doubleValue() - precoMedio) / precoMedio) * 100;
            resultado.put("diferencaPercentualDaMedia", Math.round(diferencaPercentual * 100.0) / 100.0);

            String avaliacao;
            if (diferencaPercentual <= -10) {
                avaliacao = "Excelente oportunidade - bem abaixo da media";
            } else if (diferencaPercentual < 0) {
                avaliacao = "Preco abaixo da media";
            } else if (diferencaPercentual == 0) {
                avaliacao = "Preco na media";
            } else if (diferencaPercentual <= 10) {
                avaliacao = "Preco acima da media";
            } else {
                avaliacao = "Preco bem acima da media - evite comprar agora";
            }
            resultado.put("avaliacao", avaliacao);
        }

        if (menorPreco != null && precoAtual.compareTo(menorPreco) <= 0) {
            resultado.put("menorPrecoHistorico", true);
        } else {
            resultado.put("menorPrecoHistorico", false);
        }

        return resultado;
    }
}