package com.jonas.apiprodutos.services;


import com.jonas.apiprodutos.domain.Categoria;
import com.jonas.apiprodutos.domain.Especificacoes;
import com.jonas.apiprodutos.domain.Pedidos;
import com.jonas.apiprodutos.domain.Produtos;
import com.jonas.apiprodutos.domain.Usuario;
import com.jonas.apiprodutos.repositories.CategoriaRepository;
import com.jonas.apiprodutos.repositories.EspecificacacaoRepository;
import com.jonas.apiprodutos.repositories.PedidoRepository;
import com.jonas.apiprodutos.repositories.ProdutosRepository;
import com.jonas.apiprodutos.repositories.UsuarioRepository;
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
    private ProdutosRepository produtosRepository;
    @Autowired
    private UsuarioRepository usuarioRepository;
    @Autowired
    private PedidoRepository pedidoRepository;



    public void instanciaDB() {

        Usuario user = Usuario.builder().nome("Jonas Oliveira").email("jonas@email.com").build();
        usuarioRepository.saveAll(Arrays.asList(user));

        Categoria c1 = Categoria.builder().categoria("LIVROS").build();
        categoriaRepository.saveAll(Arrays.asList(c1));

        Especificacoes e = Especificacoes.builder().peso(20.).altura(20.).largura(10.).build();
        especificacacaoRepository.saveAll(Arrays.asList(e));

        Produtos produtos = Produtos.builder().nome("Clean Code").descricao("Livro de tecnologia").categoria(c1).especificacoes(e).build();
        produtosRepository.saveAll(Collections.singletonList(produtos));

        Pedidos pedidos = new Pedidos(null, user,Arrays.asList(produtos));
        pedidoRepository.saveAll(Arrays.asList(pedidos));

    }
}
