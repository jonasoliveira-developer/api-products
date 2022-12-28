package com.jonas.apiprodutos.services;

import com.jonas.apiprodutos.domain.Categoria;
import com.jonas.apiprodutos.domain.Especificacoes;
import com.jonas.apiprodutos.domain.Produtos;
import com.jonas.apiprodutos.dtos.ProdutoDTO;
import com.jonas.apiprodutos.repositories.ProdutosRepository;
import com.jonas.apiprodutos.services.exceptions.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProdutoService {

    @Autowired
    private ProdutosRepository repository;
    @Autowired
    private CategoriaService categoriaService;
    @Autowired
    private EspecificacoesService especificacoesService;

    public Produtos findById(Integer id) {
        Optional<Produtos> obj = repository.findById(id);
        return obj.orElseThrow(() -> new ObjectNotFoundException("Objeto não encontrado. id:" + id));
    }

    public List<Produtos> findByCategoria(Integer categoria) {
        return repository.findByCategoria(categoria);
    }

    public List<Produtos> findAll(){
        return repository.findAll();
    }

    public Produtos create(Produtos obj, Integer cat, Integer esp) {
        obj.setId(null);
        Categoria categoria = categoriaService.findById(cat);
        Especificacoes especificacoes = especificacoesService.findById(esp);
        obj.setCategoria(categoria);
        obj.setEspecificacoes(especificacoes);

        return repository.save(obj);
    }

    public Produtos update(Integer id, ProdutoDTO obj) {
        Produtos newObj = findById(id);
        newObj.setNome(obj.getNome());
        newObj.setDescricao(obj.getDescricao());

        return repository.save(newObj);
    }


    public void delete(Integer id) {
        findById(id);

        try {
            repository.deleteById(id);
        }catch (DataIntegrityViolationException e) {
            throw new ObjectNotFoundException("Produto não pode ser deletado! Existem produtos associados");
        }

    }



}
