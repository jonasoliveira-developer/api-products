package com.jonas.apiprodutos.Resources;

import com.jonas.apiprodutos.domain.Categoria;
import com.jonas.apiprodutos.dtos.CategoriaDTO;
import com.jonas.apiprodutos.services.CategoriaService;
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
import java.util.stream.Collectors;

@RestController
@RequestMapping(value = "/categorias")
public class CategoriaResource {

    @Autowired
    private ModelMapper mapper;

    @Autowired
    private CategoriaService service;


    @GetMapping(value = "/{id}")
    public ResponseEntity<Categoria> findById(@PathVariable Integer id) {
        Categoria obj = service.findById(id);
        return ResponseEntity.ok().body(obj);
    }

    @GetMapping
    public ResponseEntity<List<CategoriaDTO>> findAll() {
       return ResponseEntity.ok().body(service.findAll().stream().map(
               categoria -> mapper.map(categoria, CategoriaDTO.class)).collect(Collectors.toList()));
    }
    @PostMapping
    public ResponseEntity<Categoria> create(@RequestBody Categoria obj) {
        obj = service.crate(obj);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}").buildAndExpand(obj.getId()).toUri();

        return   ResponseEntity.created(uri).body(obj);

    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<CategoriaDTO> update(
            @PathVariable Integer id, @RequestBody CategoriaDTO objDTO) {

        return ResponseEntity.ok().body(mapper.map(
                service.update(id, objDTO), CategoriaDTO.class));
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Void> delete(@PathVariable Integer id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }


}
