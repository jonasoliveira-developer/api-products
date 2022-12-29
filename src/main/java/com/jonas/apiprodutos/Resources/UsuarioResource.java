package com.jonas.apiprodutos.Resources;

import com.jonas.apiprodutos.domain.Usuario;
import com.jonas.apiprodutos.dtos.UsuarioDTO;
import com.jonas.apiprodutos.services.UsuarioService;
import io.swagger.v3.oas.annotations.Operation;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
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

    @PreAuthorize("hasAnyRole('ROLE_ADMIN','ROLE_USER')")
    @GetMapping(value = "/{id}")
    @Operation(summary = "Busca usuário pelo 'Id'.")
    public ResponseEntity<UsuarioDTO> findById(@PathVariable Integer id) {
        UsuarioDTO obj = mapper.map(service.findById(id),UsuarioDTO.class);
        return ResponseEntity.ok().body(obj);
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @GetMapping
    @Operation(summary = "Lista todos os usuários ")
    public ResponseEntity<List<UsuarioDTO>> findAll() {
        return ResponseEntity.ok().body(service.findAll().stream().map(
                categoria -> mapper.map(categoria, UsuarioDTO.class)).collect(Collectors.toList()));
    }
    @PreAuthorize("hasAnyRole('ROLE_ADMIN','ROLE_USER')")
    @PostMapping
    @Operation(summary = "Cria um novo usuário ")
    public ResponseEntity<UsuarioDTO> create(@RequestBody Usuario obj) {
        UsuarioDTO newObj = mapper.map(service.create(obj), UsuarioDTO.class);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}").buildAndExpand(obj.getId()).toUri();

        return   ResponseEntity.created(uri).body(newObj);

    }

    @PreAuthorize("hasAnyRole('ROLE_ADMIN','ROLE_USER')")
    @PutMapping(value = "/{id}")
    @Operation(summary = "Atualiza um usuário pelo 'Id' ")
    public ResponseEntity<UsuarioDTO> update(
            @PathVariable Integer id, @RequestBody UsuarioDTO objDTO) {

        return ResponseEntity.ok().body(mapper.map(
                service.update(id, objDTO), UsuarioDTO.class));
    }

    @PreAuthorize("hasAnyRole('ROLE_ADMIN','ROLE_USER')")
    @DeleteMapping(value = "/{id}")
    @Operation(summary = "Deleta um usuário pelo 'Id' ")
    public ResponseEntity<Void> delete(@PathVariable Integer id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }
}
