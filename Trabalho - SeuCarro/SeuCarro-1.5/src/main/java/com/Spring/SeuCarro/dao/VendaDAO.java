package com.Spring.SeuCarro.dao;

import com.Spring.SeuCarro.entity.Venda;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface VendaDAO extends JpaRepository<Venda, Integer> {

    // Consulta nomeada - carros por marca
    @Query(name = "vendaPorMarcaCarro")
    List<Venda> findByCarroMarca(String marca);

    // JPQL - todas as vendas dentro de um intervalo de datas especificado
    @Query("SELECT v FROM Venda v WHERE v.data BETWEEN :dataInicio AND :dataFim")
    List<Venda> findVendasEntreDatas(LocalDate dataInicio, LocalDate dataFim);

    // Native Query - todas as vendas relacionadas a carros com preço acima de um valor específico
    @Query(value = "SELECT * FROM vendas v JOIN carros c ON v.carro_id = c.id WHERE c.preco > :preco", nativeQuery = true)
    List<Venda> findVendasComPrecoMaiorQue(Double preco);

    // Consulta Spring Data JPA
    List<Venda> findByCarroModelo(String modelo);
}
