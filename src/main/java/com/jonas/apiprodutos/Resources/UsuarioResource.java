package com.jonas.apiprodutos.Resources;

import com.jonas.apiprodutos.domain.Usuario;
import com.jonas.apiprodutos.dtos.UsuarioDTO;
import com.jonas.apiprodutos.services.UsuarioService;
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
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping(value = "usuarios")
public class UsuarioResource {
    @Autowired
    private ModelMapper mapper;

    @Autowired
    private UsuarioService service;


    @GetMapping(value = "/{id}")
    @Operation(summary = "Busca usuário pelo 'Id'.")
    public ResponseEntity<Usuario> findById(@PathVariable Integer id) {
        Usuario obj = service.findById(id);
        return ResponseEntity.ok().body(obj);
    }

    @GetMapping
    @Operation(summary = "Lista todos os usuários ")
    public ResponseEntity<List<UsuarioDTO>> findAll() {
        return ResponseEntity.ok().body(service.findAll().stream().map(
                categoria -> mapper.map(categoria, UsuarioDTO.class)).collect(Collectors.toList()));
    }
    @PostMapping
    @Operation(summary = "Cria um novo usuários ")
    public ResponseEntity<Usuario> create(@RequestBody Usuario obj) {
        obj = service.crate(obj);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}").buildAndExpand(obj.getId()).toUri();

        return   ResponseEntity.created(uri).body(obj);

    }

    @PutMapping(value = "/{id}")
    @Operation(summary = "Atualiza um usuário peli 'Id' ")
    public ResponseEntity<UsuarioDTO> update(
            @PathVariable Integer id, @RequestBody UsuarioDTO objDTO) {

        return ResponseEntity.ok().body(mapper.map(
                service.update(id, objDTO), UsuarioDTO.class));
    }

    @DeleteMapping(value = "/{id}")
    @Operation(summary = "Deleta um usuário peli 'Id' ")
    public ResponseEntity<Void> delete(@PathVariable Integer id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }
}
