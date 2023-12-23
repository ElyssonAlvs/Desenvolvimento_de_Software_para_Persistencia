package com.Spring.SeuCarro.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


import java.util.List;

@NamedQuery(name = "clientePorCpf", query = "SELECT c FROM Cliente c WHERE c.cpf ilike :cpf")

@Data
@Entity
@Table(name = "clientes")
@NoArgsConstructor
@AllArgsConstructor
public class Cliente {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(nullable = false)
    private String nome;

    @Column(name = "ultimo_nome")
    private String lastName;

    @Column(unique = true, nullable = false)
    private String cpf;


    @Column(nullable = false)
    private String fone;

    @OneToMany(mappedBy = "cliente", cascade = CascadeType.ALL)
    private List<Venda> vendas;

    // Sobrescrita do método toString para exibição personalizada
    @Override
    public String toString() {
        return "Cliente [id=" + id + ", cpf=" + cpf + ", nome=" + nome + ", ultimo_nome=" + lastName + ", fone=" + fone + "]";
    }
}
