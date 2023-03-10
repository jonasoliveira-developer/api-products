package com.jonas.apiprodutos.services;


import com.jonas.apiprodutos.domain.Categoria;
import com.jonas.apiprodutos.dtos.CategoriaDTO;
import com.jonas.apiprodutos.repositories.CategoriaRepository;
import com.jonas.apiprodutos.services.exceptions.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


@Service
public class CategoriaService {

    @Autowired
    private CategoriaRepository repository;

    public Categoria findById(Integer id) {
        Optional<Categoria> obj = repository.findById(id);
        return obj.orElseThrow(() -> new ObjectNotFoundException("Objeto não encontrado. id:" + id));
    }

    public List<Categoria> findAll() {
        return repository.findAll();
    }

    public Categoria crate(Categoria obj) {
        obj.setId(null);
        obj.setCategoria(obj.getCategoria().toUpperCase());
        return repository.save(obj);
    }

    public Categoria update(Integer id, CategoriaDTO objDTO) {
        Categoria obj = findById(id);
        obj.setCategoria(objDTO.getCategoria().toUpperCase());
        return repository.save(obj);
    }


    public void delete(Integer id) {
        findById(id);

        try {
            repository.deleteById(id);
        } catch (DataIntegrityViolationException e) {
            throw new ObjectNotFoundException("Categoria não pode ser deletado! Existem produtos associados");
        }

    }
}
