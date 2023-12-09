package com.Spring.SeuCarro.entity;

import lombok.*;
import jakarta.persistence.*;
import java.time.LocalDate;
import org.springframework.data.mongodb.core.mapping.Document;


@NamedQuery(name = "vendaPorMarcaCarro", query = "SELECT v FROM Venda v WHERE v.carro.marca ilike :marca")

@Document(collection = "Vendas")
@Entity
@Data
@Table(name = "vendas")
@NoArgsConstructor
@AllArgsConstructor
@RequiredArgsConstructor
public class Venda {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;

    @ManyToOne
    private Cliente cliente;

    @ManyToOne
    private Carro carro;

    @NonNull
    private LocalDate data;

}
