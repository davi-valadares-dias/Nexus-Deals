package com.nexusdeals.nexus_deals.service;

import com.nexusdeals.nexus_deals.model.Oferta;
import com.nexusdeals.nexus_deals.model.Produto;
import com.nexusdeals.nexus_deals.repository.OfertaRepository;
import com.nexusdeals.nexus_deals.repository.ProdutoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class ComparacaoService {

    @Autowired
    private ProdutoRepository produtoRepository;

    @Autowired
    private OfertaRepository ofertaRepository;

    @Autowired
    private ScoreCompraService scoreCompraService;

    public Map<String, Object> compararProdutos(List<Long> produtoIds) {
        List<Map<String, Object>> resultados = new ArrayList<>();

        for (Long produtoId : produtoIds) {
            Produto produto = produtoRepository.findById(produtoId).orElse(null);

            if (produto == null) {
                continue;
            }

            List<Oferta> ofertas = ofertaRepository.findByProdutoId(produtoId);
            Oferta melhorOferta = encontrarMelhorOferta(ofertas);

            Map<String, Object> item = new HashMap<>();
            item.put("produtoId", produto.getId());
            item.put("nomeProduto", produto.getNome());

            if (melhorOferta == null) {
                item.put("melhorOferta", null);
                item.put("scoreCompra", 0.0);
            } else {
                Map<String, Object> score = scoreCompraService.calcularScore(melhorOferta.getId());

                item.put("melhorPreco", melhorOferta.getPreco());
                item.put("loja", melhorOferta.getLoja().getNome());
                item.put("scoreCompra", score.get("scoreTotal"));
            }

            resultados.add(item);
        }

        String vencedor = definirVencedor(resultados);

        Map<String, Object> resposta = new HashMap<>();
        resposta.put("produtos", resultados);
        resposta.put("vencedor", vencedor);

        return resposta;
    }

    private Oferta encontrarMelhorOferta(List<Oferta> ofertas) {
        Oferta melhor = null;

        for (Oferta oferta : ofertas) {
            if (melhor == null || oferta.getPreco().compareTo(melhor.getPreco()) < 0) {
                melhor = oferta;
            }
        }

        return melhor;
    }

    private String definirVencedor(List<Map<String, Object>> resultados) {
        String nomeVencedor = null;
        double maiorScore = -1;

        for (Map<String, Object> item : resultados) {
            Object scoreObj = item.get("scoreCompra");

            if (scoreObj instanceof Number) {
                double score = ((Number) scoreObj).doubleValue();

                if (score > maiorScore) {
                    maiorScore = score;
                    nomeVencedor = (String) item.get("nomeProduto");
                }
            }
        }

        return nomeVencedor;
    }
}