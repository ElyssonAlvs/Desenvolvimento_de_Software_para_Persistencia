package com.Spring.SeuCarro.entity;

import lombok.*;
import jakarta.persistence.*;
import java.time.LocalDate;

@NamedQueries({
        @NamedQuery(name = "vendaPorMarcaCarro", query = "SELECT v FROM Venda v WHERE v.carro.marca ilike :marca")
})

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
    @JoinColumn(name = "cliente_id")
    private Cliente cliente;

    @ManyToOne
    @JoinColumn(name = "carro_id")
    private Carro carro;

    @NonNull
    private LocalDate data;
}
