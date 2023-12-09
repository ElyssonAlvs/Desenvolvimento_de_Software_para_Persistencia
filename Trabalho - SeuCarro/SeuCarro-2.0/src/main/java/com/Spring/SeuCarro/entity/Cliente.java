package com.Spring.SeuCarro.entity;

import jakarta.persistence.*;
import lombok.*;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@NamedQuery(name = "clientePorCpf", query = "SELECT c FROM Cliente c WHERE c.cpf ilike :cpf")

@Document(collection = "Clientes")
@Data
@Entity
@Table(name = "clientes")
@NoArgsConstructor
@AllArgsConstructor
public class Cliente {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;

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
