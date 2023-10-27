package org.Produto.dao;

import java.util.List;

public interface GenericDAO<T> {
    // Método para salvar uma entidade no banco de dados.
    void save(T entity);

    // Método para excluir uma entidade do banco de dados.
    void delete(T entity);

    // Método para encontrar uma entidade com base em sua chave primária (ID).
    T find(Object id);

    // Método para encontrar todas as entidades do tipo T no banco de dados.
    List<T> find();

    // Método para iniciar uma transação no contexto do banco de dados.
    void beginTransaction();

    // Método para confirmar as alterações feitas durante uma transação no banco de dados.
    void commit();

    // Método para desfazer as alterações feitas durante uma transação no banco de dados.
    void rollback();

    // Método para fechar a conexão com o banco de dados ou recursos relacionados.
    void close();
}
