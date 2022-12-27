package com.jonas.apiprodutos.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class EspecificacoesDTO {

    private Integer id;
    private Double altura;
    private Double largura;
    private Double peso;


}
