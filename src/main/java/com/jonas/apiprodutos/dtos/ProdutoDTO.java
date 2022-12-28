package com.jonas.apiprodutos.dtos;

import com.jonas.apiprodutos.domain.Categoria;
import com.jonas.apiprodutos.domain.Especificacoes;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProdutoDTO {
    private Integer id;
    private String nome;
    private String descricao;
    private Categoria categoria;
    private Especificacoes especificacoes;
}
