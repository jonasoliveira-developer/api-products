package com.jonas.apiprodutos.repositories;

import com.jonas.apiprodutos.domain.Especificacoes;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EspecificacoesRepository extends JpaRepository<Especificacoes, Integer> {
}
