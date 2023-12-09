package com.Spring.SeuCarro.dao.jpa;

import com.Spring.SeuCarro.dao.ClienteDAO;
import com.Spring.SeuCarro.entity.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface ClienteJPADAO extends ClienteDAO, JpaRepository<Cliente, Integer> {

    @Query(name = "clientePorCpf")
    Cliente buscaPorCpf(@Param("cpf") String cpf);

    List<Cliente> findByNome(String nome);
}
