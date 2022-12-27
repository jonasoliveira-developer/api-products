package com.jonas.apiprodutos.services;


import com.jonas.apiprodutos.domain.Categoria;
import com.jonas.apiprodutos.repositories.CategoriaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Arrays;


@Service
public class DBService {

    @Autowired
    private CategoriaRepository categoriaRepository;
    public void instanciaDB() {

        Categoria c1 = Categoria.builder().titulo("LIVROS").build();
        categoriaRepository.saveAll(Arrays.asList(c1));

    }
}
