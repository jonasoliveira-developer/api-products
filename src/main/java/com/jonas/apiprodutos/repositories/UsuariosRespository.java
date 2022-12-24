package com.jonas.apiprodutos.repositories;

import com.jonas.apiprodutos.domain.Usuarios;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UsuariosRespository extends JpaRepository<Usuarios, Integer> {
}
