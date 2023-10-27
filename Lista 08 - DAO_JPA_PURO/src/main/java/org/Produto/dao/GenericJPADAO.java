package org.Produto.dao;

import jakarta.persistence.EntityManager;
import jakarta.persistence.criteria.CriteriaQuery;
import java.util.List;

public class GenericJPADAO<T> implements GenericDAO<T> {
    protected Class<T> persistentClass;

    // Construtor da classe que recebe a classe persistente como parâmetro.
    public GenericJPADAO(Class<T> persistentClass) {
        this.persistentClass = persistentClass;
    }

    // Método para salvar uma entidade no banco de dados.
    public void save(T entity) {
        EntityManager em = JPAUtil.getEntityManager();
        try {
            JPAUtil.beginTransaction(); // Inicia uma transação.
            em.merge(entity); // Faz o merge (atualização) da entidade no contexto persistente.
            JPAUtil.commit(); // Comita a transação, efetivando a atualização.
        } catch (Exception e) {
            JPAUtil.rollback(); // Em caso de exceção, faz o rollback da transação.
            throw new DAOException("ERRO: Não foi possível realizar a operação.", e);
        } finally {
            JPAUtil.closeEntityManager(); // Garante que o EntityManager é fechado.
        }
    }

    // Método para excluir uma entidade do banco de dados.
    public void delete(T entity) {
        EntityManager em = JPAUtil.getEntityManager();
        try {
            JPAUtil.beginTransaction(); // Inicia uma transação.
            em.remove(em.merge(entity)); // Faz o merge (atualização) e, em seguida, remove a entidade.
            JPAUtil.commit(); // Comita a transação, efetivando a remoção.
        } catch (Exception e) {
            JPAUtil.rollback(); // Em caso de exceção, faz o rollback da transação.
            throw new DAOException("ERRO: Não foi possível realizar a operação.", e);
        } finally {
            JPAUtil.closeEntityManager(); // Garante que o EntityManager é fechado.
        }
    }

    // Método para encontrar uma entidade pelo seu ID no banco de dados.
    public T find(Object id) {
        return getEm().find(persistentClass, id);
    }

    // Método para buscar todas as entidades da classe persistente.
    public List<T> find() {
        CriteriaQuery<T> cq = getEm().getCriteriaBuilder().createQuery(persistentClass);
        cq.from(persistentClass);
        return getEm().createQuery(cq).getResultList();
    }

    // Obtém o EntityManager a partir da classe de utilitário JPA.
    public EntityManager getEm() {
        return JPAUtil.getEntityManager();
    }

    // Métodos para controle de transação.
    public void beginTransaction() {
        JPAUtil.beginTransaction();
    }

    public void commit() {
        JPAUtil.commit();
    }

    public void rollback() {
        JPAUtil.rollback();
    }

    // Fecha o EntityManager.
    public void close() {
        JPAUtil.closeEntityManager();
    }
}
