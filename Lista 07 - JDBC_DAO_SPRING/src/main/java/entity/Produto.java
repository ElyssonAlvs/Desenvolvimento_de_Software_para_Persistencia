package entity;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import lombok.ToString;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Produto {
    private int id;              // Identificador único do produto
    private int codigo;          // Código do produto
    private String descricao;    // Descrição do produto
    private BigDecimal preco;    // Preço do produto (usando BigDecimal para representar números decimais com precisão)
    private int quantidade;      // Quantidade em estoque do produto
    private String ultimaEntrada; // Data da última entrada do produto no estoque
}
