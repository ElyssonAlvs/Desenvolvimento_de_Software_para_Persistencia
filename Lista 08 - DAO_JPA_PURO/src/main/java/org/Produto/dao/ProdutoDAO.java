package org.Produto.dao;

import org.Produto.entity.Produto;

import java.time.LocalDate;
import java.util.List;

public interface ProdutoDAO {
    // Salva um produto no banco de dados.
     void save(Produto entity);

    // Exclui um produto com base no código.
     void delete(int codigo);

    // Busca um produto por ‘ID’ e retorna o objeto Produto correspondente.
     Produto find(int id);

    // Retorna uma lista de todos os produtos no banco de dados.
     List<Produto> find();

    // Busca produtos por descrição e retorna uma lista de objeto Produto correspondentes.
     List<Produto> findByDescricao(String descricao);

    // Busca um produto por código e retorna o objeto Produto correspondente.
     Produto findByCodigo(int codigo);

    // Busca produtos com preço menor ou igual ao valor especificado e retorna uma lista de objeto Produto correspondentes.
     List<Produto> findByPreco(double preco);

     // Busca produtos cuja data da última entrada esteja entre as datas especificadas e retorna uma lista de objeto Produto correspondentes.
     List<Produto> findByDataUltimaEntrada(LocalDate dataInicial, LocalDate dataFinal);
}

