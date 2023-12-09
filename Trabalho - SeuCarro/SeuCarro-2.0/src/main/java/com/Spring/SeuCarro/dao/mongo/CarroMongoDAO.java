package com.Spring.SeuCarro.dao.mongo;

import com.Spring.SeuCarro.dao.CarroDAO;
import com.Spring.SeuCarro.entity.Carro;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface CarroMongoDAO extends CarroDAO,MongoRepository <Carro, String> {

    // Consulta personalizada para encontrar carros abaixo de um preço máximo
    @Query("{ 'preco' : { $lt : ?0 } }")
    List<Carro> findCarrosComPrecoMenorQue(Double maxPreco);

    // Consulta Spring Data MongoDB
    List<Carro> findByAnoFabricacao(Integer anoFabricacao);

    // Consulta Spring Data MongoDB
    List<Carro> findByAnoFabricacaoBetween(Integer anoFabricacaoInicial, Integer anoFabricacaoFinal);

    // Consulta Spring Data MongoDB
    Carro findByMarcaAndModelo(String marcaCarro, String modeloCarro);
}