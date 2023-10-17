package dao;
import entity.Produto;

import java.math.BigDecimal;
import java.sql.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class ProdutoJDBCDAO implements ProdutoDAO {
    public ProdutoJDBCDAO() {};
    public void save(Produto entity) {
        Connection con = null;
        PreparedStatement pst = null;
        try {
            con = ConnectionFactory.getConnection();
            if (entity.getId() == 0) {
                // Inserir novo produto

                // Define a instrução SQL para inserir um novo produto
                String insert_sql = "INSERT INTO produtos (codigo, descricao, preco, quantidade, ultima_entrada) VALUES (?, ?, ?, ?, ?)";
                pst = con.prepareStatement(insert_sql, Statement.RETURN_GENERATED_KEYS);

                // Define os valores dos parâmetros na instrução SQL
                pst.setInt(1, entity.getCodigo());
                pst.setString(2, entity.getDescricao());
                pst.setDouble(3, entity.getPreco().doubleValue());
                pst.setInt(4, entity.getQuantidade());

                // Converte a data da última entrada de String para java.sql.Date
                SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
                java.util.Date date = sdf.parse(entity.getUltimaEntrada());
                pst.setDate(5, new java.sql.Date(date.getTime()));
            } else {
                // Atualizar produto existente

                // Define a instrução SQL para atualizar um produto existente
                String update_sql = "UPDATE produtos SET codigo = ?, descricao = ?, preco = ?, quantidade = ?, ultima_entrada = ? WHERE id = ?";
                pst = con.prepareStatement(update_sql);

                // Define os valores dos parâmetros na instrução SQL
                pst.setInt(1, entity.getCodigo());
                pst.setString(2, entity.getDescricao());
                pst.setDouble(3, entity.getPreco().doubleValue());
                pst.setInt(4, entity.getQuantidade());

                // Converte a data da última entrada de String para java.sql.Date
                SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
                java.util.Date date = sdf.parse(entity.getUltimaEntrada());
                pst.setDate(5, new java.sql.Date(date.getTime()));

                // Define o ID do produto que está sendo atualizado
                pst.setInt(6, entity.getId());
            }

            // Executa a instrução SQL
            pst.executeUpdate();
        } catch (SQLException | ParseException e) {
            throw new DAOException("Operação não realizada com sucesso", e);
        } finally {
            try {
                if (pst != null) {
                    pst.close();
                }
                if (con != null) {
                    con.close();
                }
            } catch (SQLException e) {
                throw new DAOException("Não foi possível fechar a conexão", e);
            }
        }
    }
    public void delete(int codigo) {
        Connection con = null;
        try {
            con = ConnectionFactory.getConnection();
            String sql = "delete from produtos where codigo = ?";

            // Cria uma instrução SQL para deletar produtos com base no código
            PreparedStatement pst = con.prepareStatement(sql);
            pst.setInt(1, codigo);

            // Executa a instrução SQL para excluir o produto com o código especificado
            pst.executeUpdate();
        } catch (SQLException e) {
            // Trata exceções do tipo SQLException
            throw new DAOException("Operação não realizada com sucesso.", e);
        } finally {
            try {
                if (con != null)
                    con.close();
            } catch (SQLException e) {
                // Trata exceções que podem ocorrer ao fechar a conexão
                throw new DAOException("Não foi possível fechar a conexão.", e);
            }
        }
    }

    public Produto find(int id) {
        Connection con = null;
        Produto cl = null;
        try {
            con = ConnectionFactory.getConnection();
            String sql = "select id, codigo, descricao, preco, quantidade, ultima_entrada from produtos where id = ?";

            // Cria uma instrução SQL para selecionar um produto com base no ID
            PreparedStatement pst = con.prepareStatement(sql);
            pst.setInt(1, id);

            // Executa a instrução SQL e obtém os resultados
            ResultSet rs = pst.executeQuery();

            if (rs.next()) {
                // Se um resultado for encontrado, mapeia os dados para um objeto Produto
                cl = map(rs);
            }
        } catch (SQLException e) {
            // Trata exceções do tipo SQLException
            throw new DAOException("Operação não realizada com sucesso.", e);
        } finally {
            try {
                if (con != null)
                    con.close();
            } catch (SQLException e) {
                // Trata exceções que podem ocorrer ao fechar a conexão
                throw new DAOException("Não foi possível fechar a conexão.", e);
            }
        }

        // Retorna o objeto Produto encontrado ou null se nenhum produto corresponder ao ID especificado
        return cl;
    }
    private Produto map(ResultSet rs) throws SQLException {
        Produto cl = new Produto();

        // Mapeia os campos do resultado do ResultSet para os atributos do objeto Produto
        cl.setId(rs.getInt("id"));
        cl.setCodigo(rs.getInt("codigo"));
        cl.setDescricao(rs.getString("descricao"));

        // Verifica se o valor do preço é nulo antes de mapeá-lo como BigDecimal.
        BigDecimal preco = rs.getBigDecimal("preco");
        if (preco != null) {
            cl.setPreco(preco);
        } else {
            cl.setPreco(BigDecimal.ZERO); // Ou outra ação apropriada, dependendo dos requisitos.
        }

        cl.setQuantidade(rs.getInt("quantidade"));
        cl.setUltimaEntrada(rs.getString("ultima_entrada"));

        // Retorna o objeto Produto com os campos mapeados a partir do ResultSet
        return cl;
    }

    public List<Produto> find() {
        Connection con = null;
        List<Produto> result = null;
        try {
            con = ConnectionFactory.getConnection();
            PreparedStatement pst;

            // Consulta SQL para selecionar todos os produtos na tabela "produtos"
            String sql = "select * from produtos";
            pst = con.prepareStatement(sql);
            ResultSet rs = pst.executeQuery();

            result = new ArrayList<Produto>(); // Inicializa a lista de resultados

            // Itera pelos resultados do ResultSet
            while (rs.next()) {
                Produto cl = map(rs); // Mapeia os dados do ResultSet para um objeto Produto
                result.add(cl); // Adiciona o objeto mapeado à lista de resultados
            }
        } catch (SQLException e) {
            throw new DAOException("Operação não realizada com sucesso.", e);
        } finally {
            try {
                if (con != null)
                    con.close(); // Fecha a conexão com o banco de dados
            } catch (SQLException e) {
                throw new DAOException("Não foi possível fechar a conexão.", e);
            }
        }

        return result; // Retorna a lista de produtos encontrados no banco de dados
    }

    public List<Produto> findByDescricao(String descricao) {
        Connection con = null;
        List<Produto> produtos = new ArrayList<>();
        try {
            con = ConnectionFactory.getConnection();
            PreparedStatement pst;

            // Consulta SQL para selecionar produtos na tabela "produtos" com correspondência parcial na descrição
            String sql = "SELECT id, codigo, descricao, preco, quantidade, ultima_entrada FROM produtos WHERE descricao LIKE ?";
            pst = con.prepareStatement(sql);

            // Define o parâmetro de descrição na consulta SQL com a correspondência parcial
            String descricaoPesquisa = "%" + descricao + "%";
            pst.setString(1, descricaoPesquisa);

            ResultSet rs = pst.executeQuery();

            // Itera pelos resultados do ResultSet
            while (rs.next()) {
                Produto cl = map(rs); // Mapeia os dados do ResultSet para um objeto Produto
                produtos.add(cl); // Adiciona o objeto mapeado à lista de produtos
            }
        } catch (SQLException e) {
            throw new DAOException("Operação não realizada com sucesso.", e);
        } finally {
            try {
                if (con != null)
                    con.close(); // Fecha a conexão com o banco de dados
            } catch (SQLException e) {
                throw new DAOException("Não foi possível fechar a conexão.", e);
            }
        }
        return produtos; // Retorna a lista de produtos com correspondência parcial na descrição encontrados no banco de dados
    }

    public Produto findByCodigo(int codigo) {
        Connection con = null;
        Produto result = null;
        try {
            con = ConnectionFactory.getConnection();
            PreparedStatement pst;

            // Consulta SQL para selecionar um produto na tabela "produtos" com um código específico
            String sql = "select id, codigo, descricao, preco, quantidade, ultima_entrada from produtos where codigo = ?";
            pst = con.prepareStatement(sql);
            pst.setInt(1, codigo); // Define o parâmetro de código na consulta SQL
            ResultSet rs = pst.executeQuery();

            // Verifica se um resultado foi encontrado
            if (rs.next()) {
                result = map(rs); // Mapeia os dados do ResultSet para um objeto Produto
            }
        } catch (SQLException e) {
            throw new DAOException("Operação não realizada com sucesso.", e);
        } finally {
            try {
                if (con != null)
                    con.close(); // Fecha a conexão com o banco de dados
            } catch (SQLException e) {
                throw new DAOException("Não foi possível fechar a conexão.", e);
            }
        }

        return result; // Retorna o objeto Produto com o código especificado encontrado no banco de dados ou null se não for encontrado.
    }

    public List<Produto> findByPreco(double preco) {
        Connection con = null;
        List<Produto> result = new ArrayList<>();
        try {
            con = ConnectionFactory.getConnection();

            // Consulta SQL para selecionar produtos na tabela "produtos" com preço menor ou igual ao máximo especificado
            String sql = "SELECT * FROM produtos WHERE preco <= ?";
            PreparedStatement pst = con.prepareStatement(sql);
            pst.setDouble(1, preco); // Define o parâmetro de preço na consulta SQL
            ResultSet rs = pst.executeQuery();

            // Itera pelos resultados da consulta
            while (rs.next()) {
                Produto produto = map(rs); // Mapeia os dados do ResultSet para um objeto Produto
                result.add(produto); // Adiciona o produto à lista de resultados
            }
        } catch (SQLException e) {
            throw new DAOException("Operação não realizada com sucesso.", e);
        } finally {
            try {
                if (con != null)
                    con.close(); // Fecha a conexão com o banco de dados
            } catch (SQLException e) {
                throw new DAOException("Não foi possível fechar a conexão.", e);
            }
        }

        return result; // Retorna uma lista de produtos com preço menor ou igual ao máximo especificado.
    }

    public List<Produto> findByDataUltimaEntrada(LocalDate dataInicial, LocalDate dataFinal) {
        Connection con = null;
        List<Produto> result = new ArrayList<>();

        try {
            con = ConnectionFactory.getConnection();

            // Consulta SQL para selecionar produtos na tabela "produtos" com data de última entrada dentro do intervalo especificado
            String sql = "SELECT id, codigo, descricao, preco, quantidade, ultima_entrada FROM produtos WHERE ultima_entrada BETWEEN ? AND ?";
            PreparedStatement pst = con.prepareStatement(sql);
            pst.setDate(1, Date.valueOf(dataInicial)); // Define o primeiro parâmetro de data na consulta SQL
            pst.setDate(2, Date.valueOf(dataFinal));   // Define o segundo parâmetro de data na consulta SQL
            ResultSet rs = pst.executeQuery();

            // Itera pelos resultados da consulta
            while (rs.next()) {
                Produto produto = map(rs); // Mapeia os dados do ResultSet para um objeto Produto
                result.add(produto); // Adiciona o produto à lista de resultados
            }
        } catch (SQLException e) {
            throw new DAOException("Operação não realizada com sucesso.", e);
        } finally {
            try {
                if (con != null)
                    con.close(); // Fecha a conexão com o banco de dados
            } catch (SQLException e) {
                throw new DAOException("Não foi possível fechar a conexão.", e);
            }
        }

        return result; // Retorna uma lista de produtos com a data de última entrada dentro do intervalo especificado.
    }
}
