package org.SeuCarro.dao;

import java.util.List;

import org.SeuCarro.Entity.Carro;

public interface CarrosDAO {

    public void save(Carro entity);

    public void delete(int id);

    public Carro find(int IdCarro);

    public List<Carro> find();

    public Carro findByMarca(String marca);

    public List<Carro> findByTipoCombustivel(String TipoCombustivel);
}