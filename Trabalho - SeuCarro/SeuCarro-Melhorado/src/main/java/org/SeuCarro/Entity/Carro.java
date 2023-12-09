package org.SeuCarro.Entity;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Carro implements Serializable {
    @JsonProperty("id")
    private int id;
    @JsonProperty("modelo")
    private String modelo;
    @JsonProperty("marca")
    private String marca;
    @JsonProperty("configuracao")
    private String configuracao;
    @JsonProperty("anoFabricacao")
    private int anoFabricacao;
    @JsonProperty("tipoCombustivel")
    private String tipoCombustivel;
    @JsonProperty("preco")
    private double preco;
}
