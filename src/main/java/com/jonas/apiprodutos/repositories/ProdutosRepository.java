package com.jonas.apiprodutos.repositories;

import com.jonas.apiprodutos.domain.Produtos;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProdutosRepository extends JpaRepository<Produtos, Integer> {
}
