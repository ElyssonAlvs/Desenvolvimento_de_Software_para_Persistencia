package com.Spring.SeuCarro.dao;

import com.Spring.SeuCarro.entity.Cliente;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ClienteDAO extends JpaRepository<Cliente, Integer> {

    // Consulta nomeada "clientePorCpf" definida na classe Cliente
    @Query(name = "clientePorCpf")
    Cliente buscaPorCpf(@Param("cpf") String cpf);

    // Consulta Native Query para encontrar clientes por nome
    @Query(value = "SELECT * FROM Clientes WHERE nome = :nome", nativeQuery = true)
    List<Cliente> findByNome(String nome);

    // Consulta utilizando Spring Data JPA
    Optional<Cliente> findById(int id);
}
