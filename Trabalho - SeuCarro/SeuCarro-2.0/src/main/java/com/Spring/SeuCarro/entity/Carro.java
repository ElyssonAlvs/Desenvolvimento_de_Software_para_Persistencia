package com.Spring.SeuCarro.entity;

import jakarta.persistence.*;
import lombok.*;
import org.springframework.data.mongodb.core.mapping.Document;

@NamedQuery(name = "carroPorMarca", query = "SELECT c from Carro c WHERE c.marca ilike :marca")

@Document(collection = "Carros")
@Entity
@Data
@Table(name = "carros")
@NoArgsConstructor
@AllArgsConstructor
public class Carro {

    // Identificador único para o carro
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;

    // Marca do carro (não pode ser nula)
    @Column(nullable = false)
    private String marca;

    // Modelo do carro (não pode ser nulo)
    @Column(nullable = false)
    private String modelo;

    // Configuração específica do carro
    @Column(nullable = false)
    private String configuracao;

    // Ano de fabricação do carro (não pode ser nulo)
    @NonNull
    @Column(nullable = false)
    private Integer anoFabricacao;

    // Tipo de combustível do carro (não pode ser nulo)
    @Column(nullable = false)
    private String tipo_combustivel;

    // Cor do carro (não pode ser nula)
    @Column(nullable = false)
    private String cor;

    // Preço do carro (não pode ser nulo)
    @Column(nullable = false)
    private Double preco;

}
