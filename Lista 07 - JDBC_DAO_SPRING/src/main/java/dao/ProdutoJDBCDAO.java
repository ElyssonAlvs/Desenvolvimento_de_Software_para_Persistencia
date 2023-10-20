package dao;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import javax.sql.DataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import entity.Produto;


@Repository
public class ProdutoJDBCDAO implements ProdutoDAO {

    @Autowired
    private DataSource dataSource;

    public ProdutoJDBCDAO() {
    }

    public void save(Produto entity) {
        try (Connection con = dataSource.getConnection()) {
            String insert_sql = "INSERT INTO produtos (codigo, descricao, preco, quantidade, ultima_entrada) VALUES (?, ?, ?, ?, ?)";
            String update_sql = "UPDATE produtos SET codigo = ?, descricao = ?, preco = ?, quantidade = ?, ultima_entrada = ? WHERE id = ?";
            PreparedStatement pst;
            if (entity.getId() == 0) {
                pst = con.prepareStatement(insert_sql);
            } else {
                pst = con.prepareStatement(update_sql);
                pst.setInt(6, entity.getId());
            }
            pst.setInt(1, entity.getCodigo());
            pst.setString(2, entity.getDescricao());
            pst.setDouble(3, entity.getPreco().doubleValue());
            pst.setInt(4, entity.getQuantidade());

            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            java.util.Date date = sdf.parse(entity.getUltimaEntrada());
            pst.setDate(5, new java.sql.Date(date.getTime()));

            pst.executeUpdate();
        } catch (SQLException | ParseException e) {
            throw new DAOException("Operação não realizada com sucesso.", e);
        }
    }

    public void delete(int codigo) {
        try (Connection con = dataSource.getConnection()) {
            String sql = "DELETE FROM produtos WHERE codigo = ?";
            PreparedStatement pst = con.prepareStatement(sql);
            pst.setInt(1, codigo);
            pst.executeUpdate();
        } catch (SQLException e) {
            throw new DAOException("Operação não realizada com sucesso.", e);
        }
    }

    public Produto find(int id) {
        try (Connection con = dataSource.getConnection()) {
            String sql = "SELECT id, codigo, descricao, preco, quantidade, ultima_entrada FROM produtos WHERE id = ?";
            PreparedStatement pst = con.prepareStatement(sql);
            pst.setInt(1, id);
            ResultSet rs = pst.executeQuery();
            if (rs.next()) {
                return map(rs);
            }
        } catch (SQLException e) {
            throw new DAOException("Operação não realizada com sucesso.", e);
        }
        return null;
    }

    private Produto map(ResultSet rs) throws SQLException {
        Produto cl = new Produto();
        cl.setId(rs.getInt("id"));
        cl.setCodigo(rs.getInt("codigo"));
        cl.setDescricao(rs.getString("descricao"));

        BigDecimal preco = rs.getBigDecimal("preco");
        if (preco != null) {
            cl.setPreco(preco);
        } else {
            cl.setPreco(BigDecimal.ZERO);
        }

        cl.setQuantidade(rs.getInt("quantidade"));
        cl.setUltimaEntrada(rs.getString("ultima_entrada"));

        return cl;
    }

    public List<Produto> find() {
        List<Produto> result = new ArrayList<>();
        try (Connection con = dataSource.getConnection()) {
            String sql = "SELECT * FROM produtos";
            PreparedStatement pst = con.prepareStatement(sql);
            ResultSet rs = pst.executeQuery();
            while (rs.next()) {
                Produto cl = map(rs);
                result.add(cl);
            }
        } catch (SQLException e) {
            throw new DAOException("Operação não realizada com sucesso.", e);
        }
        return result;
    }

    public Produto findByCodigo(int codigo) {
        try (Connection con = dataSource.getConnection()) {
            String sql = "SELECT id, codigo, descricao, preco, quantidade, ultima_entrada FROM produtos WHERE codigo = ?";
            PreparedStatement pst = con.prepareStatement(sql);
            pst.setInt(1, codigo);
            ResultSet rs = pst.executeQuery();
            if (rs.next()) {
                return map(rs);
            }
        } catch (SQLException e) {
            throw new DAOException("Operação não realizada com sucesso.", e);
        }
        return null;
    }

    public List<Produto> findByPreco(double preco) {
        List<Produto> result = new ArrayList<>();
        try (Connection con = dataSource.getConnection()) {
            String sql = "SELECT * FROM produtos WHERE preco <= ?";
            PreparedStatement pst = con.prepareStatement(sql);
            pst.setDouble(1, preco);
            ResultSet rs = pst.executeQuery();
            while (rs.next()) {
                Produto produto = map(rs);
                result.add(produto);
            }
        } catch (SQLException e) {
            throw new DAOException("Operação não realizada com sucesso.", e);
        }
        return result;
    }

    public List<Produto> findByDataUltimaEntrada(LocalDate dataInicial, LocalDate dataFinal) {
        List<Produto> result = new ArrayList<>();
        try (Connection con = dataSource.getConnection()) {
            String sql = "SELECT id, codigo, descricao, preco, quantidade, ultima_entrada FROM produtos WHERE ultima_entrada BETWEEN ? AND ?";
            PreparedStatement pst = con.prepareStatement(sql);
            pst.setDate(1, java.sql.Date.valueOf(dataInicial));
            pst.setDate(2, java.sql.Date.valueOf(dataFinal));
            ResultSet rs = pst.executeQuery();
            while (rs.next()) {
                Produto produto = map(rs);
                result.add(produto);
            }
        } catch (SQLException e) {
            throw new DAOException("Operação não realizada com sucesso.", e);
        }
        return result;
    }
    public List<Produto> findByDescricao(String descricao) {
        List<Produto> produtos = new ArrayList<>();
        try (Connection con = dataSource.getConnection()) {
            String sql = "SELECT id, codigo, descricao, preco, quantidade, ultima_entrada FROM produtos WHERE descricao LIKE ?";
            PreparedStatement pst = con.prepareStatement(sql);

            String descricaoPesquisa = "%" + descricao + "%";
            pst.setString(1, descricaoPesquisa);

            ResultSet rs = pst.executeQuery();

            while (rs.next()) {
                Produto cl = map(rs);
                produtos.add(cl);
            }
        } catch (SQLException e) {
            throw new DAOException("Operação não realizada com sucesso.", e);
        }
        return produtos;
    }

}
