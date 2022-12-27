package com.jonas.apiprodutos.services;


import com.jonas.apiprodutos.domain.Categoria;
import com.jonas.apiprodutos.domain.Especificacoes;
import com.jonas.apiprodutos.domain.Produtos;
import com.jonas.apiprodutos.repositories.CategoriaRepository;
import com.jonas.apiprodutos.repositories.EspecificacacaoRepository;
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
    private EspecificacacaoRepository especificacacaoRepository;

    @Autowired
    ProdutosRepository produtosRepository;

    public void instanciaDB() {

        Categoria c1 = Categoria.builder().categoria("LIVROS").build();
        categoriaRepository.saveAll(Arrays.asList(c1));

        Especificacoes e = Especificacoes.builder().peso(20.).altura(20.).largura(10.).build();
        especificacacaoRepository.saveAll(Arrays.asList(e));

        Produtos p1 = Produtos.builder().nome("Clean Code").descricao("Livro de tecnologia").categoria(c1).especificacoes(e).build();
        produtosRepository.saveAll(Collections.singletonList(p1));

    }
}
