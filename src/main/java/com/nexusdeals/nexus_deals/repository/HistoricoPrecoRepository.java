package com.nexusdeals.nexus_deals.repository;

import com.nexusdeals.nexus_deals.model.HistoricoPreco;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface HistoricoPrecoRepository extends JpaRepository<HistoricoPreco, Long> {

    List<HistoricoPreco> findByOfertaIdOrderByDataColetaDesc(Long ofertaId);
}