package com.jonas.apiprodutos.Resources;

import com.jonas.apiprodutos.domain.Especificacoes;
import com.jonas.apiprodutos.dtos.EspecificacoesDTO;
import com.jonas.apiprodutos.services.EspecificacoesService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping(value = "/especificacoes")
public class EspecificacoesResource {

    @Autowired
    private EspecificacoesService service;
    @Autowired
    private ModelMapper mapper;

    @GetMapping("/{id}")
    public ResponseEntity<Especificacoes> findById(@PathVariable Integer id) {
        Especificacoes obj = service.findById(id);
       return ResponseEntity.ok().body(obj);

    }

    @GetMapping
    public ResponseEntity<List<Especificacoes>> findAll() {
       return ResponseEntity.ok().body(service.findall());
    }

    @PostMapping
    public  ResponseEntity<Especificacoes> create(@RequestBody Especificacoes obj) {
        Especificacoes newObj = service.create(obj);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("{id}")
                .buildAndExpand(obj.getId()).toUri();

        return ResponseEntity.created(uri).body(obj);
    }
    @PutMapping
    public ResponseEntity<EspecificacoesDTO> update(
            @PathVariable Integer id, @RequestBody EspecificacoesDTO objDTO) {
       return ResponseEntity.ok().body(
               mapper.map(service.update(id, objDTO), EspecificacoesDTO.class));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Integer id) {
        service.delete(id);
       return ResponseEntity.noContent().build();
    }

}
