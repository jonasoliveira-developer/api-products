package com.jonas.apiprodutos.services;


import com.jonas.apiprodutos.domain.Categoria;
import com.jonas.apiprodutos.domain.Produtos;
import com.jonas.apiprodutos.repositories.CategoriaRepository;
import com.jonas.apiprodutos.repositories.ProdutosRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.Collections;


@Service
public class DBService {

    @Autowired
    private CategoriaRepository categoriaRepository;
    @Autowired
    ProdutosRepository produtosRepository;
    public void instanciaDB() {

        Categoria c1 = Categoria.builder().titulo("LIVROS").build();
        categoriaRepository.saveAll(Arrays.asList(c1));

        Produtos p1 = Produtos.builder().nome("Clean Code").descricao("Livro de tecnologia").categoria(c1).build();
        produtosRepository.saveAll(Collections.singletonList(p1));

    }
}
