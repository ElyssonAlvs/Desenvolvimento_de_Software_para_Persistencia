package org.SeuCarro.Entity;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;
import java.io.Serializable;

/**
 * Representa um carro.
 */
@Getter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Setter
public class Carro implements Serializable {

    /**
     * Identificador único do carro.
     */
    @JsonProperty("id")
    private int id;

    /**
     * Modelo do carro.
     */
    @JsonProperty("modelo")
    private String modelo;

    /**
     * Marca do carro.
     */
    @JsonProperty("marca")
    private String marca;

    /**
     * Configuração do carro.
     */
    @JsonProperty("configuracao")
    private String configuracao;

    /**
     * Ano de fabricação do carro.
     */
    @JsonProperty("anoFabricacao")
    private int anoFabricacao;

    /**
     * Tipo de combustível do carro.
     */
    @JsonProperty("tipoCombustivel")
    private String tipoCombustivel;

    /**
     * Preço do carro.
     */
    @JsonProperty("preco")
    private double preco;
}
