package dao;

import entity.Produto;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import org.springframework.stereotype.Repository;

@Repository
public class ProdutoListDAO implements ProdutoDAO {

    private final List<Produto> produtos;
    private static int idProximo = 1;

    public ProdutoListDAO() {
        this.produtos = new ArrayList<Produto>();
    }

    public void save(Produto entity) {
        // Insere um produto se o ID do objeto for 0.
        if (entity.getId() == 0) {
            entity.setId(idProximo++); // Define um novo ID para o produto.
            produtos.add(entity); // Adiciona o produto à lista de produtos.
        } else {
            // Atualiza um produto se o ID não for 0.
            int posicaoNaLista = findIndex(entity.getId()); // Encontra a posição do produto na lista pelo ID.
            produtos.set(posicaoNaLista, entity); // Substitui o produto existente na posição encontrada com o novo produto.
        }
    }


    public void delete(int id) {
        // Remove um produto com o código especificado.
        produtos.remove(find(id));
    }

    public Produto find(int id) {
        // Busca um produto pelo ID.
        for (Produto cl : this.produtos) {
            if (cl.getId() == id) {
                return cl; // Retorna o produto se encontrado.
            }
        }
        return null; // Retorna null se o produto não for encontrado.
    }


    private int findIndex(int id) {
        // Retorna o índice na lista do produto com o ID especificado.
        for (int i = 0; i < produtos.size(); i++) {
            if (produtos.get(i).getId() == id) {
                return i; // Retorna o índice do produto se encontrado.
            }
        }
        return -1; // Retorna -1 se o produto não for encontrado.
    }


    public List<Produto> find() {
        // Retorna uma lista de todos os produtos.
        return this.produtos;
    }

    public List<Produto> findByDescricao(String descricao) {
        // Retorna uma lista de produtos com a descrição especificada.
        List<Produto> produtosComDescricao = new ArrayList<>();
        for (Produto cl : this.produtos) {
            if (cl.getDescricao().equals(descricao)) {
                produtosComDescricao.add(cl);
            }
        }
        return produtosComDescricao;
    }

    public Produto findByCodigo(int codigo) {
        // Retorna o produto com o código especificado.
        for (Produto cl : this.produtos) {
            if (cl.getCodigo() == codigo) {
                return cl;
            }
        }
        return null; // Retorna null se o produto não for encontrado com o código especificado.
    }

    public List<Produto> findByPreco(double preco) {
        // Retorna uma lista de produtos com preço menor ou igual ao máximo especificado.
        List<Produto> resultado = new ArrayList<>();
        BigDecimal precoMaximo = BigDecimal.valueOf(preco);
        for (Produto produto : produtos) {
            /*
               Se o preço do produto for igual ao máximo -> igual a zero (entre na lógica)
               Se o preço for menor que o preço máximo -> valor negativo (entra na lógica)
               Se o preço for mairo que o preço méximo -> valor positivo (não entra na lógica
             */
            if (produto.getPreco().compareTo(precoMaximo) <= 0) {
                resultado.add(produto);
            }
        }
        return resultado;
    }

    public List<Produto> findByDataUltimaEntrada(LocalDate dataInicial, LocalDate dataFinal) {
        // Cria uma lista para armazenar os produtos que se encaixam no intervalo de datas especificado.
        List<Produto> produtosPorData = new ArrayList<>();
        // Itera sobre a lista de produtos existentes.
        for (Produto produto : produtos) {
            // Converte a data de última entrada do produto em um objeto LocalDate.
            LocalDate dataUltimaEntrada = LocalDate.parse(produto.getUltimaEntrada());
            // Verifica se a data de última entrada não é antes da data inicial e não é depois da data final.
            if (!dataUltimaEntrada.isBefore(dataInicial) && !dataUltimaEntrada.isAfter(dataFinal)) {
                // Se a data de última entrada estiver dentro do intervalo, adiciona o produto à lista de produtosPorData.
                produtosPorData.add(produto);
            }
        }
        // Retorna a lista de produtos cuja data de última entrada está dentro do intervalo especificado.
        return produtosPorData;
    }
}
