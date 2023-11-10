package com.Spring.SeuCarro.dao;

import com.Spring.SeuCarro.entity.Carro;
import org.springframework.context.annotation.Primary;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@Primary
public interface CarroDAO extends JpaRepository<Carro, Integer> {

    // Consulta nomeada "carroPorMarca" está definida na classe Carro
    @Query(name = "carroPorMarca")
    List<Carro> findByMarca(String marca);

    // JPQL retorna uma lista de carro abaixo de um preço máximo
    @Query("SELECT c FROM Carro as c WHERE c.preco < :maxPreco")
    List<Carro> findCarrosComPrecoMenorQue(Double maxPreco);

    // Native Query
    @Query("SELECT c FROM Carro c WHERE c.anoFabricacao = :anoFabricacao")
    List<Carro> findByAnoFabricacao(@Param("anoFabricacao") Integer anoFabricacao);

    // Consulta Spring Data JPA
    List<Carro> findByAnoFabricacaoBetween(Integer anoFabricacaoInicial, Integer anoFabricacaoFinal);

    Carro findByMarcaAndModelo(String marcaCarro, String modeloCarro);
}
