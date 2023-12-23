package com.Spring.SeuCarro.dao.jpa;

import com.Spring.SeuCarro.dao.VendaDAO;
import com.Spring.SeuCarro.entity.Venda;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.time.LocalDate;
import java.util.List;

@Repository
public interface VendaJPADAO extends VendaDAO, JpaRepository<Venda, String> {

    List<Venda> findByCarroMarca(String marca);

    List<Venda> findByDataBetween(LocalDate dataInicio, LocalDate dataFim);

    List<Venda> findByCarroModelo(String modelo);
}
