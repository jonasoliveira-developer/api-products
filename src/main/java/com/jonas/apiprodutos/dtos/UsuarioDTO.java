package com.jonas.apiprodutos.dtos;

import com.jonas.apiprodutos.domain.Role;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.stream.Collectors;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UsuarioDTO {
   private Integer id;
   private String nome;
   private String email;
   private List<Role> role;

}



