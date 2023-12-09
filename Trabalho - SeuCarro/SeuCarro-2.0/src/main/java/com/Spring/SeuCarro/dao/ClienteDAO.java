package com.Spring.SeuCarro.dao;

import com.Spring.SeuCarro.entity.Cliente;

import java.util.List;
import java.util.Optional;

public interface ClienteDAO {
        //-------Métodos das interfaces--------------\\
        public List<Cliente> findByNome(String nome);

        public Cliente buscaPorCpf(String cpf);

    public void deleteById(String id);

    public List<Cliente> findAll();

    public Optional<Cliente> findById(String id);

    public void delete(Cliente cl);

    public void save(Cliente cl);
}
