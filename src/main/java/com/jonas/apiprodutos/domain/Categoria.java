package com.jonas.apiprodutos.domain;

import lombok.AllArgsConstructor;

import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.apache.el.stream.Stream;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import java.util.ArrayList;
import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Categoria {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private  Integer id;

    @Column(nullable = true)
    private String descricao;

    @OneToMany(mappedBy = "categoria")
    private List<Produtos> produtos = new ArrayList<>();

}
