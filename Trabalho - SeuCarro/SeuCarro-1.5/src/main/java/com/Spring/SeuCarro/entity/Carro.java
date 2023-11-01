package com.Spring.SeuCarro.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@Data
@Table(name = "carros")
@NoArgsConstructor
@AllArgsConstructor
@RequiredArgsConstructor
public class Carro {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String modelo;
    private String marca;
    private String configuracao;
    private int ano_fabricacao;
    private String tipo_combustivel;
    private double preco;


}
