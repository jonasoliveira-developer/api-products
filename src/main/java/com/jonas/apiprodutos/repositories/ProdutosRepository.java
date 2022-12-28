package com.jonas.apiprodutos.repositories;

import com.jonas.apiprodutos.domain.Produtos;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProdutosRepository extends JpaRepository<Produtos, Integer> {

    @Query(value = "SELECT * FROM PRODUTOS AS P WHERE P.CATEGORIA_ID=?1", nativeQuery = true )
    List<Produtos> findByCategoria(Integer categoria);
}
