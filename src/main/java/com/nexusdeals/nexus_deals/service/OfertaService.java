package com.nexusdeals.nexus_deals.service;

import com.nexusdeals.nexus_deals.model.HistoricoPreco;
import com.nexusdeals.nexus_deals.model.Oferta;
import com.nexusdeals.nexus_deals.repository.HistoricoPrecoRepository;
import com.nexusdeals.nexus_deals.repository.OfertaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class OfertaService {

    @Autowired
    private OfertaRepository ofertaRepository;

    @Autowired
    private HistoricoPrecoRepository historicoPrecoRepository;

    public List<Oferta> listarTodas() {
        return ofertaRepository.findAll();
    }

    public List<Oferta> buscarPorProduto(Long produtoId) {
        return ofertaRepository.findByProdutoId(produtoId);
    }

    public Oferta buscarPorId(Long id) {
        return ofertaRepository.findById(id).orElse(null);
    }

    public Oferta salvar(Oferta oferta) {
        return ofertaRepository.save(oferta);
    }

    public void deletar(Long id) {
        ofertaRepository.deleteById(id);
    }

    public Map<String, Object> buscarComConfiabilidade(Long id) {
        Oferta oferta = ofertaRepository.findById(id).orElse(null);

        if (oferta == null) {
            Map<String, Object> erro = new HashMap<>();
            erro.put("erro", "Oferta nao encontrada");
            return erro;
        }

        List<HistoricoPreco> historico = historicoPrecoRepository.findByOfertaIdOrderByDataColetaDesc(id);

        Map<String, Object> resultado = new HashMap<>();
        resultado.put("oferta", oferta);

        if (historico.isEmpty()) {
            resultado.put("ultimaAtualizacao", null);
            resultado.put("statusAtualizacao", "Sem historico de coleta registrado");
            resultado.put("frescor", "desconhecido");
        } else {
            LocalDateTime ultimaColeta = historico.get(0).getDataColeta();
            long horasDesdeUltimaColeta = Duration.between(ultimaColeta, LocalDateTime.now()).toHours();

            resultado.put("ultimaAtualizacao", ultimaColeta);
            resultado.put("horasDesdeUltimaAtualizacao", horasDesdeUltimaColeta);

            String frescor;
            if (horasDesdeUltimaColeta <= 24) {
                frescor = "atualizado";
            } else if (horasDesdeUltimaColeta <= 72) {
                frescor = "levemente desatualizado";
            } else {
                frescor = "desatualizado";
            }
            resultado.put("frescor", frescor);
        }

        boolean freteInformado = oferta.getFrete() != null;
        resultado.put("freteInformado", freteInformado);
        if (!freteInformado) {
            resultado.put("avisoFrete", "Preco pode nao incluir frete");
        }

        return resultado;
    }
}