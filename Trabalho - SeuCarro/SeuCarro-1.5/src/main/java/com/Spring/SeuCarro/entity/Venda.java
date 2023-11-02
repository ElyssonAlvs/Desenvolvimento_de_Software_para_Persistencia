package com.Spring.SeuCarro.entity;

import lombok.*;

import jakarta.persistence.*;
import java.time.LocalDate;
import java.util.List;

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
    private Carro carro;

    @OneToOne
    private Cliente cliente;

    @NonNull
    private LocalDate data;
}
