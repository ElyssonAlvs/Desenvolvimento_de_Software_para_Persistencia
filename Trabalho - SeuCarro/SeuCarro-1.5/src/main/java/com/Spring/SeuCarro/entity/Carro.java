package com.Spring.SeuCarro.entity;

import jakarta.persistence.*;
import lombok.*;

@NamedQuery(name = "carroPorMarca", query = "SELECT c from Carro c WHERE c.marca ilike :marca")


@Entity
@Data
@Table(name = "carros")
@NoArgsConstructor
@AllArgsConstructor
public class Carro {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(nullable = false)
    private String marca;

    @Column(nullable = false)
    private String modelo;

    @Column(nullable = false)
    private String configuracao;

    @NonNull
    @Column(nullable = false)
    private Integer anoFabricacao;

    @Column(nullable = false)
    private String tipo_combustivel;

    @Column(nullable = false)
    private String cor;

    @Column(nullable = false)
    private Double preco;

}
