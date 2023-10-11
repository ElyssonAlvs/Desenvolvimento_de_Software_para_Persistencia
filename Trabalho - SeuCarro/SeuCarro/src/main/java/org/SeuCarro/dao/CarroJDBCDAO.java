package org.SeuCarro.dao;
import org.SeuCarro.Entity.Carro;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CarroJDBCDAO implements CarrosDAO {

    public CarroJDBCDAO(){}

    public void save(Carro entity) {
        Connection con = null;
        try {
            con = ConnectionFactory.getConnection();
            String insert_sql = "insert into carro (modelo, marca, configuracao, anofabricacao, tipocombustive, preco) values (?, ?, ?, ?, ?, ?)";
            String update_sql = "update carro set modelo = ?, marca = ?, configuracao = ?, anofabricacao = ?, tipocombustivel = ?, preco = ? where IdCarro = ?";
            PreparedStatement pst;
            if (entity.getIdCarro() == 0) {
                pst = con.prepareStatement(insert_sql);
            } else {
                pst = con.prepareStatement(update_sql);
                pst.setInt(5, entity.getIdCarro());
            }
            pst.setString(1, entity.getModelo());
            pst.setString(2, entity.getMarca());
            pst.setString(3, entity.getConfiguracao());
            pst.setString(4, String.valueOf(entity.getAnoFabricacao()));
            pst.setString(5, entity.getTipoCombustivel());
            pst.setString(6, String.valueOf(new BigDecimal(entity.getPreco())));
            pst.executeUpdate();
        } catch (SQLException e) {
            throw new DAOException("Operação não realizada com sucesso.", e);
        } finally {
            try {
                if (con != null)
                    con.close();
            } catch (SQLException e) {
                throw new DAOException("Não foi possível fechar a conexão.",e);
            }
        }
    }

    public void delete(int id) {
        Connection con = null;
        try {
            con = ConnectionFactory.getConnection();
            String sql = "delete from carro where IdCarro = ?";
            PreparedStatement pst = con.prepareStatement(sql);
            pst.setInt(1, id);
            pst.executeUpdate();
        } catch (SQLException e) {
            throw new DAOException("Operação não realizada com sucesso.", e);
        } finally {
            try {
                if (con != null)
                    con.close();
            } catch (SQLException e) {
                throw new DAOException("Não foi possível fechar a conexão.",e);
            }
        }
    }

    public Carro find(int IdCarro) {
        Connection con = null;
        Carro cl = null;
        try {
            con = ConnectionFactory.getConnection();
            String sql = "select IdCarro,modelo, marca, configuracao, anofabricacao, tipocombustive, preco from carro where IdCarro = ?";
            PreparedStatement pst = con.prepareStatement(sql);
            pst.setInt(1, IdCarro);
            ResultSet rs = pst.executeQuery();
            if (rs.next()) {
                cl = map(rs);
            }
        } catch (SQLException e) {
            throw new DAOException("Operação não realizada com sucesso.", e);
        } finally {
            try {
                if (con != null)
                    con.close();
            } catch (SQLException e) {
                throw new DAOException("Não foi possível fechar a conexão.",e);
            }
        }
        return cl;
    }

    private Carro map(ResultSet rs) throws SQLException {
        Carro cl = new Carro();
        cl.setIdCarro(rs.getInt("IdCarro"));
        cl.setModelo(rs.getString("modelo"));
        cl.setMarca(rs.getString("marca"));
        cl.setConfiguracao(rs.getString("configuracao"));
        cl.setAnoFabricacao(rs.getInt("anofabricacao"));
        cl.setTipoCombustivel(rs.getString("tipocombustivel"));
        cl.setPreco(rs.getBigDecimal("preco").doubleValue());
        return cl;
    }

    public List<Carro> find() {
        Connection con = null;
        List<Carro> result = null;
        try {
            con = ConnectionFactory.getConnection();
            PreparedStatement pst;
            String sql = "select IdCarro, modelo, marca, configuracao, anofabricacao, tipocombustive from carro";
            pst = con.prepareStatement(sql);
            ResultSet rs = pst.executeQuery();
            result = new ArrayList<Carro>();
            while (rs.next()) {
                Carro cl = map(rs);
                result.add(cl);
            }
        } catch (SQLException e) {
            throw new DAOException("Operação não realizada com sucesso.", e);
        } finally {
            try {
                if (con != null)
                    con.close();
            } catch (SQLException e) {
                throw new DAOException("Não foi possível fechar a conexão.",e);
            }
        }
        return result;
    }

    public Carro findByMarca(String marca) {
        Connection con = null;
        Carro cl = null;
        try {
            con = ConnectionFactory.getConnection();
            PreparedStatement pst;
            String sql = "select IdCarro, modelo, configuracao, anofabricacao, tipocombustive from carro where marca = ?";
            pst = con.prepareStatement(sql);
            pst.setString(1, marca);
            ResultSet rs = pst.executeQuery();
            if (rs.next()) {
                cl = map(rs);
            }
        } catch (SQLException e) {
            throw new DAOException("Operação não realizada com sucesso.", e);
        } finally {
            try {
                if (con != null)
                    con.close();
            } catch (SQLException e) {
                throw new DAOException("Não foi possível fechar a conexão.",e);
            }
        }
        return cl;
    }

    public List<Carro> findByTipoCombustivel(String TipoCombustivel) {
        Connection con = null;
        List<Carro> result = null;
        try {
            con = ConnectionFactory.getConnection();
            PreparedStatement pst;
            String sql = "select IdCarro, modelo, marca, configuracao, anofabricacao, tipocombustive from carro where upper(tipocombustivel) like ?";
            pst = con.prepareStatement(sql);
            pst.setString(1, "%" + TipoCombustivel.toUpperCase() + "%");
            ResultSet rs = pst.executeQuery();
            result = new ArrayList<Carro>();
            while (rs.next()) {
                Carro cl = map(rs);
                result.add(cl);
            }
        } catch (SQLException e) {
            throw new DAOException("Operação não realizada com sucesso.", e);
        } finally {
            try {
                if (con != null)
                    con.close();
            } catch (SQLException e) {
                throw new DAOException("Não foi possível fechar a conexão.",e);
            }
        }
        return result;
    }
}
