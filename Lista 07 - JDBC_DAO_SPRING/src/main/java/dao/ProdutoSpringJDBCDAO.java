package dao;

import java.time.LocalDate;
import java.util.List;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import entity.Produto;
import lombok.extern.slf4j.Slf4j;

@Repository
@Primary
@Slf4j
public class ProdutoSpringJDBCDAO implements ProdutoDAO {

	@Autowired
	private NamedParameterJdbcTemplate jdbcTemplate;

	public ProdutoSpringJDBCDAO() {}

	@Override
	public void save(Produto entity) {
		// Define a consulta SQL para inserir ou atualizar um produto no banco de dados
		String insert_sql = "INSERT INTO produtos (codigo, descricao, preco, quantidade, ultima_entrada) VALUES (:codigo, :descricao, :preco, :quantidade, :ultimaEntrada)";
		String update_sql = "UPDATE produtos SET codigo = :codigo, descricao = :descricao, preco = :preco, quantidade = :quantidade, ultima_entrada = :ultimaEntrada WHERE id = :id";

		// Cria um objeto MapSqlParameterSource para passar parâmetros para a consulta
		MapSqlParameterSource params = new MapSqlParameterSource()
				.addValue("codigo", entity.getCodigo())
				.addValue("descricao", entity.getDescricao())
				.addValue("preco", entity.getPreco())
				.addValue("quantidade", entity.getQuantidade())
				.addValue("ultimaEntrada", entity.getUltimaEntrada());

		if (entity.getId() == 0) {
			// Se o ID do produto for zero, insira um novo produto no banco de dados
			jdbcTemplate.update(insert_sql, params);
		} else {
			// Caso contrário, atualize o produto existente no banco de dados
			params.addValue("id", entity.getId());
			jdbcTemplate.update(update_sql, params);
		}
	}

	@Override
	public void delete(int id) {
		// Define a consulta SQL para excluir um produto com base no ID
		String sql = "DELETE FROM produtos WHERE id = :id";

		// Cria um objeto MapSqlParameterSource para passar o ID como parâmetro
		MapSqlParameterSource params = new MapSqlParameterSource()
				.addValue("id", id);

		// Executa a consulta de exclusão
		jdbcTemplate.update(sql, params);
	}

	@Override
	public Produto find(int id) {
		Produto produto = null;
		try {
			// Define a consulta SQL para buscar um produto por ID
			String sql = "SELECT id, codigo, descricao, preco, quantidade, ultima_entrada FROM produtos WHERE id = :id";

			// Cria um objeto MapSqlParameterSource com o ID como parâmetro
			MapSqlParameterSource params = new MapSqlParameterSource()
					.addValue("id", id);

			// Executa a consulta e mapeia o resultado para um objeto Produto
			produto = jdbcTemplate.queryForObject(sql, params, (rs, rowNum) -> map(rs));
		} catch (EmptyResultDataAccessException e) {
			// Trata exceção caso nenhum resultado seja encontrado
			log.debug(e.getMessage());
		}
		return produto;
	}

	@Override
	public List<Produto> find() {
		// Define a consulta SQL para buscar todos os produtos
		String sql = "SELECT id, codigo, descricao, preco, quantidade, ultima_entrada FROM produtos";

		// Executa a consulta e mapeia o resultado para uma lista de objetos Produto
		return jdbcTemplate.query(sql, (rs, rowNum) -> map(rs));
	}

	@Override
	public Produto findByCodigo(int codigo) {
		Produto produto = null;
		try {
			// Define a consulta SQL para buscar um produto por código
			String sql = "SELECT id, codigo, descricao, preco, quantidade, ultima_entrada FROM produtos WHERE codigo = :codigo";

			// Cria um objeto MapSqlParameterSource com o código como parâmetro
			MapSqlParameterSource params = new MapSqlParameterSource()
					.addValue("codigo", codigo);

			// Executa a consulta e mapeia o resultado para um objeto Produto
			produto = jdbcTemplate.queryForObject(sql, params, (rs, rowNum) -> map(rs));
		} catch (EmptyResultDataAccessException e) {
			// Trata exceção caso nenhum resultado seja encontrado
			log.debug(e.getMessage());
		}
		return produto;
	}

	@Override
	public List<Produto> findByDescricao(String descricao) {
		// Define a consulta SQL para buscar produtos por descrição
		String sql = "SELECT id, codigo, descricao, preco, quantidade, ultima_entrada FROM produtos WHERE descricao = :descricao";

		// Cria um objeto MapSqlParameterSource com a descrição como parâmetro
		MapSqlParameterSource params = new MapSqlParameterSource()
				.addValue("descricao", descricao);

		// Executa a consulta e mapeia o resultado para uma lista de objetos Produto
		return jdbcTemplate.query(sql, params, (rs, rowNum) -> map(rs));
	}

	@Override
	public List<Produto> findByPreco(double preco) {
		// Define a consulta SQL para buscar produtos com preço menor ou igual ao valor especificado
		String sql = "SELECT id, codigo, descricao, preco, quantidade, ultima_entrada FROM produtos WHERE preco <= :preco";

		// Cria um objeto MapSqlParameterSource com o preço como parâmetro
		MapSqlParameterSource params = new MapSqlParameterSource()
				.addValue("preco", preco);

		// Executa a consulta e mapeia o resultado para uma lista de objetos produtos
		return jdbcTemplate.query(sql, params, (rs, rowNum) -> map(rs));
	}

	@Override
	public List<Produto> findByDataUltimaEntrada(LocalDate dataInicial, LocalDate dataFinal) {
		// Define a consulta SQL para buscar produtos com data de última entrada entre as datas especificadas
		String sql = "SELECT id, codigo, descricao, preco, quantidade, ultima_entrada FROM produtos WHERE ultima_entrada BETWEEN :dataInicial AND :dataFinal";

		// Cria objetos MapSqlParameterSource com as datas como parâmetros
		MapSqlParameterSource params = new MapSqlParameterSource()
				.addValue("dataInicial", dataInicial)
				.addValue("dataFinal", dataFinal);

		// Executa a consulta e mapeia o resultado para uma lista de objetos Produto
		return jdbcTemplate.query(sql, params, (rs, rowNum) -> map(rs));
	}

	// Método privado para mapear os resultados do banco de dados para um objeto Produto
	private Produto map(ResultSet rs) throws SQLException {
		Produto produto = new Produto();
		produto.setId(rs.getInt("id"));
		produto.setCodigo(rs.getInt("codigo"));
		produto.setDescricao(rs.getString("descricao"));
		produto.setPreco(rs.getBigDecimal("preco"));
		produto.setQuantidade(rs.getInt("quantidade"));
		produto.setUltimaEntrada(rs.getString("ultima_entrada"));
		return produto;
	}
}
