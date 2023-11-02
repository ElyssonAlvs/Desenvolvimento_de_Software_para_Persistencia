package com.Spring.SeuCarro.entity;

import jakarta.persistence.*;
import lombok.*;
import java.util.List;

@NamedQueries({
        @NamedQuery(name = "clientePorCpf", query = "SELECT c FROM Cliente c WHERE c.cpf = :cpf")
})


@Data
@Entity
@Table(name = "cliente")
@NoArgsConstructor
@AllArgsConstructor
@RequiredArgsConstructor
public class Cliente {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String nome;
    private String sobrenome;

    @Column(unique = true, nullable = false)
    private String cpf;

    private String fone;

    @OneToMany(mappedBy = "cliente")
    private List<Carro> carro_comprado;

    @Override
    public String toString() {
        return "Cliente [id=" + id + ", cpf=" + cpf + ", nome=" + nome + ", fone=" + fone + "]";
    }
}
