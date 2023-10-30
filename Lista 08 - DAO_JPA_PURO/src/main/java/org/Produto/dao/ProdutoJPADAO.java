package org.Produto.dao;

import java.time.LocalDate;
import java.util.List;
import jakarta.persistence.EntityManager;
import org.Produto.entity.Produto;

public class ProdutoJPADAO extends GenericJPADAO<Produto> implements ProdutoDAO {
    // Chama o construtor da classe pai e especifica a classe de entidade como Produto.
    public ProdutoJPADAO() { super(Produto.class) ;}
    // Remove um produto com base no código passado (não implementado corretamente).
    public void delete(int codigo) {
        EntityManager em = JPAUtil.getEntityManager();
        em.getTransaction().begin();

        // Consulta JPQL para excluir o produto com o código fornecido.
        em.createQuery("delete from Produto p where p.codigo = :codigo")
                .setParameter("codigo", codigo)
                .executeUpdate();

        em.getTransaction().commit();
    }

    // Encontra um produto por ID.
    public Produto find(int id) { return find(Integer.valueOf(id)); }
    public Produto findByCodigo(int codigo) {
        EntityManager em = JPAUtil.getEntityManager();
        // Consulta JPQL para encontrar um produto por código.
        List<Produto> produtos = em
                .createQuery("select p from Produto p where p.codigo = :codigo", Produto.class)
                .setParameter("codigo", codigo)
                .getResultList();
        JPAUtil.closeEntityManager();
        return produtos.isEmpty() ? null : produtos.get(0);
    }
    public List<Produto> findByDescricao(String descricao) {
        EntityManager em = JPAUtil.getEntityManager();
        // Consulta JPQL para encontrar produtos onde a descrição (ignorando maiúsculas/minúsculas) contém o texto especificado.
        List<Produto> produtos = em
                .createQuery("select p from Produto p where upper(p.descricao) like upper(:descricao)", Produto.class)
                .setParameter("descricao", "%" + descricao + "%")
                .getResultList();

        JPAUtil.closeEntityManager();
        return produtos; // Retorna a lista de produtos encontrados.
    }
    public List<Produto> findByPreco(double preco) {
        EntityManager em = JPAUtil.getEntityManager();
        // Consulta JPQL para encontrar produtos com preços menores ou iguais ao valor especificado.
        List<Produto> produtos = em
                .createQuery("select p from Produto p where p.preco <= :preco", Produto.class)
                .setParameter("preco", preco)
                .getResultList();
        JPAUtil.closeEntityManager();
        return produtos;
    }
    public List<Produto> findByDataUltimaEntrada(LocalDate dataInicial, LocalDate dataFinal) {
        EntityManager em = JPAUtil.getEntityManager();
        // Consulta JPQL para encontrar produtos com base na data de última entrada.
        List<Produto> produtos = em
                .createQuery("select p from Produto p where p.ultima_entrada between :dataInicial and :dataFinal", Produto.class)
                .setParameter("dataInicial", dataInicial)
                .setParameter("dataFinal", dataFinal)
                .getResultList();
        JPAUtil.closeEntityManager();
        return produtos;
    }
}
