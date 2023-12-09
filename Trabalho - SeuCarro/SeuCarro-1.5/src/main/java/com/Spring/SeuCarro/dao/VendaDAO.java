package com.Spring.SeuCarro.dao;

import com.Spring.SeuCarro.entity.Venda;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Repository
public interface VendaDAO extends JpaRepository<Venda, Integer> {

    // Consulta nomeada - carros por marca
    @Query(name = "vendaPorMarcaCarro")
    List<Venda> findByCarroMarca(String marca);

    // JPQL - todas as vendas em um intervalo de datas especificado
    @Query("SELECT v FROM Venda v WHERE v.data BETWEEN :dataInicio AND :dataFim")
    List<Venda> findVendasEntreDatas(LocalDate dataInicio, LocalDate dataFim);

    // Consulta Spring Data JPA
    List<Venda> findByCarroModelo(String modelo);

    @Override
    Optional<Venda> findById(Integer id);
}
