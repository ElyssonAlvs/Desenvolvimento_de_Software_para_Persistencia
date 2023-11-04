package com.Spring.SeuCarro.entity;

import jakarta.persistence.*;
import lombok.*;

@NamedQuery(name = "carroPorMarca", query = "SELECT c from Carro c WHERE c.marca ilike :marca")


@Entity
@Data
@Table(name = "carros")
@NoArgsConstructor
@AllArgsConstructor
// @RequiredArgsConstructor
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

    @Column(nullable = false)
    private Integer ano_fabricacao;

    @Column(nullable = false)
    private String tipo_combustivel;

    @Column(nullable = false)
    private String cor;

    @Column(nullable = false)
    private Double preco;

    @ManyToOne
    @JoinColumn(name = "cliente_id")
    private Cliente cliente;

}
