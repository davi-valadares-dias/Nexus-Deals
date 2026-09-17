package com.nexusdeals.nexus_deals.service;

import com.nexusdeals.nexus_deals.model.Loja;
import com.nexusdeals.nexus_deals.repository.LojaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LojaService {

    @Autowired
    private LojaRepository lojaRepository;

    public List<Loja> listarTodas(){
        return lojaRepository.findAll();
    }

    public Loja buscarPorId(Long id){
        return lojaRepository.findById(id).orElse(null);
    }

    public Loja salvar(Loja loja){
        return lojaRepository.save(loja);
    }

    public void deletar(Long id){
        lojaRepository.deleteById(id);
    }
}
