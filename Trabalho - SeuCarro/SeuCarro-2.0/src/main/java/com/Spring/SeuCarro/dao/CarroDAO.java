package com.Spring.SeuCarro.dao;

import java.util.List;
import com.Spring.SeuCarro.entity.Carro;

public interface CarroDAO {
        //----------Métodos da interfaces-----------\\
        public List<Carro> findCarrosComPrecoMenorQue(Double maxPreco);

        public List<Carro> findByAnoFabricacao(Integer anoFabricacao);

        public List<Carro> findByAnoFabricacaoBetween(Integer anoFabricacaoInicial, Integer anoFabricacaoFinal);

        public Carro findByMarcaAndModelo(String marcaCarro, String modeloCarro);
    
    public List<Carro> findAll();

    public Carro findByModelo(String modelo);

    public Carro deleteByModelo(String modelo);

    public void save(Carro carro);

    public void delete(Carro carro);
}
