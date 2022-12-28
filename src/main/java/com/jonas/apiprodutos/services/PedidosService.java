package com.jonas.apiprodutos.services;

import com.jonas.apiprodutos.domain.Categoria;
import com.jonas.apiprodutos.domain.Pedidos;
import com.jonas.apiprodutos.dtos.CategoriaDTO;
import com.jonas.apiprodutos.dtos.PedidosDTO;
import com.jonas.apiprodutos.repositories.CategoriaRepository;
import com.jonas.apiprodutos.repositories.PedidoRepository;
import com.jonas.apiprodutos.repositories.ProdutosRepository;
import com.jonas.apiprodutos.services.exceptions.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class PedidosService {

    @Autowired
    private PedidoRepository repository;
    @Autowired
    ProdutoService produtoService;
    @Autowired
    UsuarioService usuarioService;

    public Pedidos findById(Integer id) {
        Optional<Pedidos> obj = repository.findById(id);
        return obj.orElseThrow(() -> new ObjectNotFoundException("Objeto não encontrado. id:" + id));
    }

    public List<Pedidos> findAll(){
        return repository.findAll();
    }

    public Pedidos create(PedidosDTO obj) {

        Pedidos newObj = new Pedidos();
        newObj.setUsuario(usuarioService.findById(obj.getUsuario_id()));
        newObj.getProdutos().addAll(
                obj.getProdutos().stream().map(
                        produtos -> produtoService.findById(produtos)).collect(Collectors.toList())
        );
        return repository.save(newObj);
    }

    public void delete(Integer id) {
        findById(id);

        try {
            repository.deleteById(id);
        }catch (DataIntegrityViolationException e) {
            throw new ObjectNotFoundException("Categoria não pode ser deletado! Existem produtos associados");
        }

    }
}
