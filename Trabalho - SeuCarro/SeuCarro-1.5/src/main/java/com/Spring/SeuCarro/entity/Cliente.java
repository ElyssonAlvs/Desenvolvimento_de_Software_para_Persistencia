package com.Spring.SeuCarro.entity;

import jakarta.persistence.*;
import lombok.*;
import java.util.List;

@NamedQueries({
        @NamedQuery(name = "clientePorCpf", query = "SELECT c FROM Cliente c WHERE c.cpf ilike :cpf")
})

@Data
@Entity
@Table(name = "clientes")
@NoArgsConstructor
@AllArgsConstructor
public class Cliente {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String nome;
    @Column(name = "last_name")
    private String lastName;

    @Column(unique = true, nullable = false)
    private String cpf;

    private String fone;

    @OneToMany(mappedBy = "cliente")
    private List<Carro> carro_comprado;

    @OneToMany(mappedBy = "cliente")
    private List<Venda> vendas;

    @Override
    public String toString() {
        return "Cliente [id=" + id + ", cpf=" + cpf + ", nome=" + nome + ", fone=" + fone + "]";
    }
}
