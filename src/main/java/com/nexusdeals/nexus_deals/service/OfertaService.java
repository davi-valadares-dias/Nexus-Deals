package com.nexusdeals.nexus_deals.service;

import com.nexusdeals.nexus_deals.model.Oferta;
import com.nexusdeals.nexus_deals.repository.OfertaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OfertaService {
    @Autowired
    private OfertaRepository ofertaRepository;

    public List<Oferta> listarTodas() {
        return ofertaRepository.findAll();
    }
    public List<Oferta> buscarPorProduto(Long produtoId){
        return ofertaRepository.findByProdutoId(produtoId);
    }
    public Oferta buscarPorId(Long id){
        return ofertaRepository.findById(id).orElse(null);
    }
    public Oferta salvar(Oferta oferta){
        return ofertaRepository.save(oferta);
    }
    public void deletar(Long id){
        ofertaRepository.deleteById(id);
    }
}
