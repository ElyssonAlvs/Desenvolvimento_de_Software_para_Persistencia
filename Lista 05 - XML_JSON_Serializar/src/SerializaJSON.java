/*
 * Crie uma classe Java de nome SerializaJSON para instanciar objetos da classe definida na Questão 1 
 * e adicionar esses objetos em uma Lista. Depois percorrer a lista e Serializar os objetos em disco/ssd. 
 * Serialize usando a Serialização de objetos da biblioteca Jackson.
 */

import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class SerializaJSON {
    public static void main(String[] args) throws IOException {
        // Criando uma lista de carros
        List<Carro> carros = new ArrayList<>();
        carros.add(new Carro(1, "Civic", "Honda", 2021));
        carros.add(new Carro(2, "Camry", "Toyota", 2016));
        carros.add(new Carro(3, "Gol Cli", "Volkswagen", 1994));

        try {
            // Configurando o ObjectMapper para lidar com JSON
            ObjectMapper objectMapper = new ObjectMapper();

            // Serializando a lista de carros em formato JSON e escrevendo no arquivo
            objectMapper.writeValue(new File("carros.json"), carros);

            System.out.println("Serialização para JSON concluída.");
        } catch (IOException e) {
            System.out.println("Erro : " + e.getMessage());
            e.printStackTrace();
        }
    }
}
