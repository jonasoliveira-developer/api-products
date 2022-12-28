package com.jonas.apiprodutos.Resources;

import com.jonas.apiprodutos.domain.Produtos;
import com.jonas.apiprodutos.dtos.ProdutoDTO;
import com.jonas.apiprodutos.services.ProdutoService;
import io.swagger.v3.oas.annotations.Operation;
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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping(value = "produtos")
public class ProdutoResource {

    @Autowired
    private ProdutoService service;
    @Autowired
    private ModelMapper mapper;

    @GetMapping("/{id}")
    @Operation(summary = "Busca produto pelo 'Id'.")
    public ResponseEntity<Produtos> findById(@PathVariable Integer id) {
        Produtos obj = service.findById(id);
        return ResponseEntity.ok().body(obj);

    }

    @GetMapping
    @Operation(summary = "Lista produtos pela 'categoria'.")
    public ResponseEntity<List<Produtos>> findByCategoria(
            @RequestParam(value = "categoria") Integer categoria) {
        List<Produtos> obj = service.findByCategoria(categoria);
        return ResponseEntity.ok().body(obj);
    }

    @GetMapping(value = "/all")
    @Operation(summary = "Lista todos os produtos")
    public ResponseEntity<List<Produtos>> findAll() {
        return ResponseEntity.ok().body(service.findAll());
    }

    @PostMapping
    @Operation(summary = "Cria um novo produto")
    public ResponseEntity<Produtos> create(
            @RequestParam(value = "categoria", required = false) Integer cat,
            @RequestParam(value = "especificacoes") Integer esp, @RequestBody Produtos obj) {
        Produtos newObj = service.create(obj, cat, esp);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("{id}")
                .buildAndExpand(obj.getId()).toUri();
        return ResponseEntity.created(uri).body(obj);
    }

    @PutMapping(value = "/{id}")
    @Operation(summary = "Atualiza um produto buscando pelo 'Id'")
    public ResponseEntity<ProdutoDTO> update(
            @PathVariable Integer id, @RequestBody ProdutoDTO objDTO) {
        return ResponseEntity.ok().body(
                mapper.map(service.update(id, objDTO), ProdutoDTO.class));
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Deleta um produto pelo 'Id'.")
    public ResponseEntity<Void> delete(@PathVariable Integer id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }

}
