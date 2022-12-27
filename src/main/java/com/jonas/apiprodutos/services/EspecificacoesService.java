package com.jonas.apiprodutos.services;

import com.jonas.apiprodutos.domain.Especificacoes;
import com.jonas.apiprodutos.dtos.EspecificacoesDTO;
import com.jonas.apiprodutos.repositories.EspecificacacaoRepository;
import com.jonas.apiprodutos.services.exceptions.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EspecificacoesService {
    @Autowired
    private EspecificacacaoRepository repository;

    public Especificacoes findById(Integer id) {
       Optional<Especificacoes> obj = repository.findById(id);
       return obj.orElseThrow(() -> new ObjectNotFoundException("Objeto não encontrado! Id:" + id));
    }

    public List<Especificacoes> findall() {
        return repository.findAll();
    }

    public Especificacoes create(Especificacoes obj) {
        obj.setId(null);
        return repository.save(obj);
    }
    public Especificacoes update(Integer id, EspecificacoesDTO objDTO) {

       Especificacoes newObj = findById(id);

       newObj.setPeso(objDTO.getPeso());
       newObj.setAltura(objDTO.getAltura());
       newObj.setLargura(objDTO.getLargura());

       return repository.save(newObj);
    }

    public void delete(Integer id) {
        findById(id);
        try {
            repository.deleteById(id);
        }catch (DataIntegrityViolationException e){
            throw new com.jonas.apiprodutos.services.exceptions.DataIntegrityViolationException(
                    "Especificações não pode ser deletado! Existem produtos associados");
        }
    }
}
