package com.jonas.apiprodutos.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PedidosDTO {
    private Integer usuario_id;
    private List<Integer> produtos;
}
