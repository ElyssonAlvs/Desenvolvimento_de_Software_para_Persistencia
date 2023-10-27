package org.Produto.dao;

import java.time.LocalDate;
import java.util.List;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import org.Produto.entity.Produto;

public class ProdutoJPADAO extends GenericJPADAO<Produto> implements ProdutoDAO {

    public ProdutoJPADAO() {
        super(Produto.class); // Chama o construtor da classe pai e especifica a classe de entidade como Produto.
    }

    public void delete(int codigo) {
        delete(new Produto()); // Remove um produto com base no código passado (não implementado corretamente).
    }

    public Produto find(int id) {
        return find(Integer.valueOf(id)); // Encontra um produto por ID.
    }

    public Produto findByCodigo(int codigo) {
        EntityManager em = JPAUtil.getEntityManager();
        String jpql = "select p from Produto p where p.codigo = :codigo"; // Consulta JPQL para encontrar um produto por código.
        TypedQuery<Produto> query = em.createQuery(jpql, Produto.class);
        query.setParameter("codigo", codigo); // Define o parâmetro "codigo" na consulta.
        Produto produto = query.getSingleResult(); // Executa a consulta e obtém um único resultado (deve existir apenas um produto com o mesmo código).
        JPAUtil.closeEntityManager();
        return produto; // Retorna o produto encontrado.
    }

    public List<Produto> findByDescricao(String descricao) {
        EntityManager em = JPAUtil.getEntityManager();
        String jpql = "select p from Produto p where upper(p.descricao) like upper(:descricao)"; // Consulta JPQL para encontrar produtos com base na descrição.
        TypedQuery<Produto> query = em.createQuery(jpql, Produto.class);
        query.setParameter("descricao", "%" + descricao + "%"); // Define o parâmetro "descricao" na consulta.
        List<Produto> produtos = query.getResultList(); // Executa a consulta e obtém uma lista de produtos correspondentes.
        JPAUtil.closeEntityManager();
        return produtos; // Retorna a lista de produtos encontrados.
    }

    public List<Produto> findByPreco(double preco) {
        EntityManager em = JPAUtil.getEntityManager();
        String jpql = "select p from Produto p where p.preco <= :preco"; // Consulta JPQL para encontrar produtos com preços menores ou iguais ao valor especificado.
        TypedQuery<Produto> query = em.createQuery(jpql, Produto.class);
        query.setParameter("preco", preco); // Define o parâmetro "preco" na consulta.
        List<Produto> produtos = query.getResultList(); // Executa a consulta e obtém uma lista de produtos correspondentes.
        JPAUtil.closeEntityManager();
        return produtos; // Retorna a lista de produtos encontrados.
    }

    public List<Produto> findByDataUltimaEntrada(LocalDate dataInicial, LocalDate dataFinal) {
        EntityManager em = JPAUtil.getEntityManager();
        String jpql = "select p from Produto p where p.ultima_entrada between :dataInicial and :dataFinal"; // Consulta JPQL para encontrar produtos com base na data de última entrada.
        TypedQuery<Produto> query = em.createQuery(jpql, Produto.class);
        query.setParameter("dataInicial", dataInicial); // Define o parâmetro "dataInicial" na consulta.
        query.setParameter("dataFinal", dataFinal); // Define o parâmetro "dataFinal" na consulta.
        List<Produto> produtos = query.getResultList(); // Executa a consulta e obtém uma lista de produtos correspondentes.
        JPAUtil.closeEntityManager();
        return produtos; // Retorna a lista de produtos encontrados.
    }
}
