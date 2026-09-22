package com.nexusdeals.nexus_deals.repository;

import com.nexusdeals.nexus_deals.model.Alerta;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AlertaRepository extends JpaRepository<Alerta, Long> {

    List<Alerta> findByAtivoTrue();

    List<Alerta> findByOfertaId(Long ofertaId);
}