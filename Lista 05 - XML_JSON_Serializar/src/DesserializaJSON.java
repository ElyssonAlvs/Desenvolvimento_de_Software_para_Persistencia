/*
 * Crie uma classe java de nome DesserializaJSON para ler / desserializar 
 * os objetos Serializados na Questão 6 e exibi-los.
 */

import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.*;
import java.util.List;

public class DesserializaJSON {
    public static void main(String[] args) throws IOException {
        try {
            // Crie um ObjectMapper para mapear JSON para objetos Java
            ObjectMapper objectMapper = new ObjectMapper();

            // Leia o arquivo JSON contendo a lista serializada de carros
            File arquivoJson = new File("carros.json");

            // Desserializar a lista de carros diretamente em uma lista de objetos Carro
            List<Carro> carros = objectMapper.readValue(arquivoJson, objectMapper.getTypeFactory().constructCollectionType(List.class, Carro.class));

            // Exiba os carros desserializados
            for (Carro carro : carros) {
                System.out.println(carro);
            }

            System.out.println("Desserialização a partir do JSON concluída.");
        } catch (IOException e) {
            System.out.println("Erro : " + e.getMessage());
            e.printStackTrace();
        }
    }
}
