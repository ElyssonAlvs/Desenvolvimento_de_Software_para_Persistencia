package com.Spring.SeuCarro.dao;

import com.Spring.SeuCarro.entity.Carro;
import com.Spring.SeuCarro.entity.Cliente;
import com.Spring.SeuCarro.entity.Venda;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public interface VendaDAO {
        //----------Métodos das interfaces--------\\
        public List<Venda> findByCarroMarca(String marca);

        public List<Venda> findByDataBetween(LocalDate dataInicio, LocalDate dataFim);

        public List<Venda> findByCarroModelo(String modelo);

    public void save(Venda venda);

    public void deleteById(Venda venda);

    public List<Venda> findAll();

    public Optional<Venda> findById(int idVenda);

    public List<Venda> findByClienteAndCarro(Cliente cliente, Carro carro);

    public void delete(Venda venda);

}
