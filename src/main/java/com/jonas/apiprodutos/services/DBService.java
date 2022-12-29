package com.jonas.apiprodutos.services;


import com.jonas.apiprodutos.domain.Categoria;
import com.jonas.apiprodutos.domain.Especificacoes;
import com.jonas.apiprodutos.domain.Pedidos;
import com.jonas.apiprodutos.domain.Produtos;
import com.jonas.apiprodutos.domain.Role;
import com.jonas.apiprodutos.domain.Usuario;
import com.jonas.apiprodutos.domain.enums.RoleEnum;
import com.jonas.apiprodutos.repositories.CategoriaRepository;
import com.jonas.apiprodutos.repositories.EspecificacacaoRepository;
import com.jonas.apiprodutos.repositories.PedidoRepository;
import com.jonas.apiprodutos.repositories.ProdutosRepository;
import com.jonas.apiprodutos.repositories.RoleRepository;
import com.jonas.apiprodutos.repositories.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;


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

    @Autowired
    private BCryptPasswordEncoder encoder;

    @Autowired
    private RoleRepository roleRepository;



    public void instanciaDB() {

        Role role_admin = Role.builder().roles(RoleEnum.ROLE_ADMIN).build();

        roleRepository.save(role_admin);


        Usuario user = Usuario.builder().nome(
                "Jonas Oliveira").email("jonas@email.com").senha(encoder.encode("1234")).role(Arrays.asList(role_admin)).build();
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
