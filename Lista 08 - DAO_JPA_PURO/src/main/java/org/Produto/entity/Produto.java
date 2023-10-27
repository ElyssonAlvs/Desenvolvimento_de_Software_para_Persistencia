package org.Produto.entity;

import lombok.Data;
import jakarta.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDate;

@Entity // Indica que esta classe é uma entidade JPA (persistente).
@Data // Geração automática de métodos getters, setters, equals, hashCode e toString.
@Table(name = "Produtos") // Especifica o nome da tabela no banco de dados.

public class Produto {
    @Id // Indica que este campo é a chave primária.
    @GeneratedValue(strategy = GenerationType.IDENTITY) // Configura a estratégia de geração de valores para a chave primária.
    private int id; // Identificador único para o produto.
    private int codigo; // Código do produto.
    private String descricao; // Descrição do produto.
    private BigDecimal preco; // Preço do produto, usando o tipo BigDecimal para precisão financeira.
    private int quantidade; // Quantidade disponível do produto.
    private LocalDate ultima_entrada; // Data da última entrada do produto.
}
