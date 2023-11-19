package com.Spring.SeuCarro.entity;

import lombok.*;
import jakarta.persistence.*;
import java.time.LocalDate;


@NamedQuery(name = "vendaPorMarcaCarro", query = "SELECT v FROM Venda v WHERE v.carro.marca ilike :marca")

@Entity
@Data
@Table(name = "vendas")
@NoArgsConstructor
@AllArgsConstructor
@RequiredArgsConstructor
public class Venda {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @ManyToOne
    private Cliente cliente;

    @ManyToOne
    private Carro carro;

    @NonNull
    private LocalDate data;

}
