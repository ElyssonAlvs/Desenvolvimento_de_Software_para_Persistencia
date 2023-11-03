package com.Spring.SeuCarro.entity;

import jakarta.persistence.*;
import lombok.*;

@NamedQueries({
        @NamedQuery(name = "carroPorMarca", query = "SELECT c from Carro c WHERE c.marca ilike %:marca%")
})

@Entity
@Data
@Table(name = "carros")
@NoArgsConstructor
@AllArgsConstructor
@RequiredArgsConstructor
public class Carro {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @NonNull
    @Column(nullable = false)
    private String marca;

    @NonNull
    @Column(nullable = false)
    private String modelo;

    @NonNull
    @Column(nullable = false)
    private String configuracao;

    @NonNull
    @Column(nullable = false)
    private Integer ano_fabricacao;

    @NonNull
    @Column(nullable = false)
    private String tipo_combustivel;

    @NonNull
    @Column(nullable = false)
    private String cor;

    @NonNull
    @Column(nullable = false)
    private Double preco;

}
