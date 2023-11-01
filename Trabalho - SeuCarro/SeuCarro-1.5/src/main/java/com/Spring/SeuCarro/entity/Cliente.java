package com.Spring.SeuCarro.entity;

import jakarta.persistence.*;
import lombok.*;
import java.util.List;

@NamedQueries({
        @NamedQuery(name = "clientePorCpf", query = "select c from Cliente c where c.cpf = :cpf")
})

@Data
@Entity
@Table(name = "clientes")
@NoArgsConstructor
@AllArgsConstructor
@RequiredArgsConstructor
public class Cliente {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String nome;
    @Column(unique = true, nullable = false)
    private String cpf;
    private String fone;

    @OneToMany(mappedBy = "cliente")
    private List<Compra> compras;
}
