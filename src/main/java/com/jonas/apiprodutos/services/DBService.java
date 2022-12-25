package com.jonas.apiprodutos.services;


import com.jonas.apiprodutos.domain.Categoria;
import com.jonas.apiprodutos.domain.Especificacoes;
import com.jonas.apiprodutos.domain.Pedidos;
import com.jonas.apiprodutos.domain.Produtos;
import com.jonas.apiprodutos.domain.Usuarios;
import com.jonas.apiprodutos.repositories.CategoriaRepository;
import com.jonas.apiprodutos.repositories.EspecificacoesRepository;
import com.jonas.apiprodutos.repositories.PedidosRepository;
import com.jonas.apiprodutos.repositories.ProdutosRepository;
import com.jonas.apiprodutos.repositories.UsuariosRespository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DBService {
    @Autowired
    private UsuariosRespository usuariosRespository;
    @Autowired
    private EspecificacoesRepository especificacoesRepository;
    @Autowired
    private CategoriaRepository categoriaRepository;
    @Autowired
    private ProdutosRepository produtosRepository;
    @Autowired
    private PedidosRepository pedidosRepository;


    public void instanciaDB() {

        Usuarios u = Usuarios.builder().id(null).nome("Jonas Oliveira").email("jonas@email").build();
      Especificacoes e = Especificacoes.builder().id(null).peso(7.5).altura(7.0).largura(7.0).build();
      Categoria c = Categoria.builder().id(null).descricao("CASA").build();
      Produtos p = Produtos.builder().id(null)
              .nome("Churrasqueira").descricao("Churrasqueira média 20L").categoria(c).especificacoes(e).build();
        Pedidos pedidos = new Pedidos(null, List.of(p),u);

        usuariosRespository.save(u);
        especificacoesRepository.save(e);
        categoriaRepository.save(c);
        produtosRepository.save(p);
        pedidosRepository.save(pedidos);





    }
}
