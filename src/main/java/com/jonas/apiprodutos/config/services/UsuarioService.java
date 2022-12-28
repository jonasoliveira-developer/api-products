package com.jonas.apiprodutos.config.services;

import com.jonas.apiprodutos.domain.Usuario;
import com.jonas.apiprodutos.dtos.UsuarioDTO;
import com.jonas.apiprodutos.repositories.UsuarioRepository;
import com.jonas.apiprodutos.config.services.exceptions.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UsuarioService {
    @Autowired
    private UsuarioRepository repository;

    public Usuario findById(Integer id) {
        Optional<Usuario> obj = repository.findById(id);
        return obj.orElseThrow(() -> new ObjectNotFoundException("Objeto não encontrado. id:" + id));
    }

    public List<Usuario> findAll(){
        return repository.findAll();
    }

    public Usuario crate(Usuario obj) {
        obj.setId(null);
        return repository.save(obj);
    }

    public Usuario update(Integer id, UsuarioDTO objDTO) {
        Usuario newObj = findById(id);

        newObj.setNome(objDTO.getNome());
        newObj.setEmail(objDTO.getEmail());

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
