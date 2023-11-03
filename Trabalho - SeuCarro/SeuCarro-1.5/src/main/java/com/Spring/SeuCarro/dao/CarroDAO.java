package com.Spring.SeuCarro.dao;

import com.Spring.SeuCarro.entity.Carro;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CarroDAO extends JpaRepository<Carro, Integer> {

    // Consulta nomeada "carroPorMarca" está definida na classe Carro
    @Query(name = "carroPorMarca")
    List<Carro> findByMarca(String marca);

    // JPQL retorna uma lista de carro abaixo de um preço máximo
    @Query("SELECT c FROM Carro as c WHERE c.preco < :maxPreco")
    List<Carro> findCarrosComPrecoMenorQue(Double maxPreco);

    // Native Query para encontrar por ano de fabricação
    @Query(value = "SELECT c FROM Carro as c WHERE c.ano_fabricacao = :ano_fabricacao", nativeQuery = true)
    List<Carro> findByAnoFabricacao(Integer ano_fabicacao);

    // Consulta Spring Data JPA
    List<Carro> findByAnoFabricacaoBetween(Integer startAno, Integer endAno);

}
