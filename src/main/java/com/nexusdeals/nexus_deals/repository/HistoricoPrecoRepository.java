package com.nexusdeals.nexus_deals.repository;

import com.nexusdeals.nexus_deals.model.HistoricoPreco;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.math.BigDecimal;
import java.util.List;

public interface HistoricoPrecoRepository extends JpaRepository<HistoricoPreco, Long> {

    List<HistoricoPreco> findByOfertaIdOrderByDataColetaDesc(Long ofertaId);
    @Query("SELECT MIN(h.preco) FROM HistoricoPreco h WHERE h.oferta.id = :ofertaId")
    BigDecimal buscarMenorPreco(@Param("ofertaId") Long ofertaId);

    @Query("SELECT MAX(h.preco) FROM HistoricoPreco h WHERE h.oferta.id = :ofertaId")
    BigDecimal buscarMaiorPreco(@Param("ofertaId") Long ofertaId);

    @Query("SELECT AVG(h.preco) FROM HistoricoPreco h WHERE h.oferta.id = :ofertaId")
    Double buscarPrecoMedio(@Param("ofertaId") Long ofertaId);
}