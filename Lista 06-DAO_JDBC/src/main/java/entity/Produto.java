package entity;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import java.math.BigDecimal;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Produto {
    int id;              // Identificador único do produto
    int codigo;          // Código do produto
    String descricao;    // Descrição do produto
    BigDecimal preco;    // Preço do produto (usando BigDecimal para representar números decimais com precisão)
    int quantidade;      // Quantidade em estoque do produto
    String ultimaEntrada; // Data da última entrada do produto no estoque
}
