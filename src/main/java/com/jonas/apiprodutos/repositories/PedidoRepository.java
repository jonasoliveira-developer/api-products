package com.jonas.apiprodutos.repositories;

import com.jonas.apiprodutos.domain.Pedidos;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PedidoRepository extends JpaRepository<Pedidos,Integer> {
}
