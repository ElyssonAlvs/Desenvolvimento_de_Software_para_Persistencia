package org.Produto.dao;

import java.util.Properties;
import jakarta.persistence.*;
import org.Produto.main.Config;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class JPAUtil {
    private static final EntityManagerFactory emf;

    private static final ThreadLocal<EntityManager> ems = new ThreadLocal<>();

    static {
        Properties props = Config.getConfig(); // Carrega as propriedades de configuração.
        String persistenceUnit = props.getProperty("persistence.unit");
        log.info("persistence.unit: {}", persistenceUnit);
        emf = Persistence.createEntityManagerFactory(persistenceUnit); // Cria o EntityManagerFactory.
    }

    /**
     * Obtém o EntityManager vinculado à Thread atual. Se não existir, é criado e vinculado à Thread atual.
     */
    public static EntityManager getEntityManager() {
        EntityManager em = ems.get(); // Obtém o EntityManager da ThreadLocal.
        if (em == null) {
            em = emf.createEntityManager(); // Se não existir, cria um novo EntityManager.
            ems.set(em); // Vincula o novo EntityManager à Thread atual.
        }
        return em;
    }

    /**
     * Fecha o EntityManager atrelado à Thread atual e retira-o da ThreadLocal.
     */
    public static void closeEntityManager() {
        EntityManager em = ems.get(); // Obtém o EntityManager da ThreadLocal.
        if (em != null) {
            EntityTransaction tx = em.getTransaction();
            if (tx.isActive()) {
                tx.commit(); // Comita qualquer transação ativa.
            }
            em.close(); // Fecha o EntityManager.
            ems.remove(); // Remove o EntityManager da ThreadLocal.
        }
    }

    /**
     * Inicia uma nova transação.
     */
    public static void beginTransaction() {
        getEntityManager().getTransaction().begin(); // Inicia a transação.
    }

    /**
     * Comita a transação atual.
     */
    public static void commit() {
        EntityTransaction tx = getEntityManager().getTransaction();
        if (tx.isActive()) {
            tx.commit(); // Comita a transação ativa.
        }
    }

    /**
     * Faz rollback na transação atual.
     */
    public static void rollback() {
        EntityTransaction tx = getEntityManager().getTransaction();
        if (tx.isActive()) {
            tx.rollback(); // Faz rollback na transação ativa.
        }
    }
}
