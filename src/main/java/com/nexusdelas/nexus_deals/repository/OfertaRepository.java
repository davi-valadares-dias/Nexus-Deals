package com.nexusdeals.nexus_deals.repository;

import com.nexusdeals.nexus_deals.model.Oferta;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface OfertaRepository extends JpaRepository<Oferta, Long> {

    List<Oferta> findByProdutoId(Long produtoId);
}