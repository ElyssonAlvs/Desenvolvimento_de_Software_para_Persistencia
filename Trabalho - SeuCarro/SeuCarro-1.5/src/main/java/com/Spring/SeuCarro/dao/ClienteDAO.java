package com.Spring.SeuCarro.dao;

import com.Spring.SeuCarro.entity.Cliente;
import com.Spring.SeuCarro.entity.Carro;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.*;

@Repository
public interface ClienteDAO extends JpaRepository<Carro, Integer> {

    // A consulta nomeada "clientePorCpf" está definida na classe Cliente
    @Query(name = "clientePorCpf")
    public Cliente buscaPorCpf(String cpf);

    // JPQL retorna uma lista de clientes que possuem um número de telefone não nulo.
    @Query("SELECT c FROM Cliente c WHERE c.fone IS NOT NULL")
    List<Cliente> findClientesComTelefone();

    //  Consulta Native Query para encontrar clientes por nome
    @Query(value = "SELECT * FROM cliente WHERE nome = :nome", nativeQuery = true)
    List<Cliente> findByNome(String nome);






}
