package com.Spring.SeuCarro.dao.mongo;

import com.Spring.SeuCarro.dao.VendaDAO;
import com.Spring.SeuCarro.entity.Venda;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;
import java.time.LocalDate;
import java.util.List;

@Repository
public interface VendaMongoDAO extends VendaDAO, MongoRepository<Venda, String> {

    @Query("{ 'carro.marca' : ?0 }")
    List<Venda> findByCarroMarca(String marca);

    @Query("{ 'data' : { $gte : ?0, $lte : ?1 } }")
    List<Venda> findByDataBetween(LocalDate dataInicio, LocalDate dataFim);

    @Query("{ 'carro.modelo' : ?0 }")
    List<Venda> findByCarroModelo(String modelo);
}
