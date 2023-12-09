package com.Spring.SeuCarro.dao.mongo;

import com.Spring.SeuCarro.dao.ClienteDAO;
import com.Spring.SeuCarro.entity.Cliente;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface ClienteMongoDAO extends ClienteDAO, MongoRepository<Cliente, String> {

    @Query("{ 'cpf' : ?0 }")
    Cliente buscaPorCpf(String cpf);

    @Query("{ 'nome' : ?0 }")
    List<Cliente> findByNome(String nome);
}
