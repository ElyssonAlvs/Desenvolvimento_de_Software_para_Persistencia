package dao;

import java.time.LocalDate;
import java.util.List;
import entity.Produto;

// Interface ProdutoDAO define os métodos que devem ser implementados por classes DAO relacionadas a produtos.
public interface ProdutoDAO {

    // Salva um produto no banco de dados.
    public void save(Produto entity);

    // Exclui um produto com base no código.
    public void delete(int codigo);

    // Busca um produto por ID e retorna o objeto Produto correspondente.
    public Produto find(int id);

    // Retorna uma lista de todos os produtos no banco de dados.
    public List<Produto> find();

    // Busca produtos por descrição e retorna uma lista de objetos Produto correspondentes.
    public List<Produto> findByDescricao(String descricao);

    // Busca um produto por código e retorna o objeto Produto correspondente.
    public Produto findByCodigo(int codigo);

    // Busca produtos com preço menor ou igual ao valor especificado e retorna uma lista de objetos Produto correspondentes.
    public List<Produto> findByPreco(double preco);

    // Busca produtos cuja data da última entrada esteja entre as datas especificadas e retorna uma lista de objetos Produto correspondentes.
    public List<Produto> findByDataUltimaEntrada(LocalDate dataInicial, LocalDate dataFinal);
}
