package com.nexusdeals.nexus_deals.repository;

import com.nexusdeals.nexus_deals.model.Produto;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProdutoRepository extends JpaRepository<Produto, Long> {
}