package org.SeuCarro;

import org.SeuCarro.Entity.Carro;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class CsvDataLer {
    private static final String NOME_ARQUIVO_CSV = "carros.csv";

    // Método para ler dados do arquivo CSV e retorná-los como uma lista de objetos Carro
    public static List<Carro> lerDados() throws IOException {
        List<Carro> carros = new ArrayList<>(); // Inicializa uma lista para armazenar os objetos Carro

        // Abre o arquivo CSV para leitura usando BufferedReader
        try (BufferedReader br = new BufferedReader(new FileReader(NOME_ARQUIVO_CSV))) {
            String linha; // Variável para armazenar cada linha lida do arquivo

            // Lê cada linha do arquivo enquanto houver linhas disponíveis
            while ((linha = br.readLine()) != null) {
                String[] partes = linha.split(","); // Divide a linha em partes usando ',' como separador

                // Verifica se a linha contém exatamente 7 partes (os atributos do objeto Carro)
                if (partes.length == 7) {
                    // Converte as partes em valores apropriados para criar um objeto Carro
                    int id = Integer.parseInt(partes[0]);
                    String modelo = partes[1];
                    String marca = partes[2];
                    String configuracao = partes[3];
                    int anoFabricacao = Integer.parseInt(partes[4]);
                    String tipoCombustivel = partes[5];
                    double preco = Double.parseDouble(partes[6]);

                    // Cria um objeto Carro com os valores extraídos e adiciona à lista de carros
                    Carro carro = new Carro(id, modelo, marca, configuracao, anoFabricacao, tipoCombustivel,preco);
                    carros.add(carro);
                }
            }
        }catch (IOException e){
            System.out.println("Erro ao ler o arquivo csv " + e.getMessage());
        }

        // Retorna a lista de carros lidos do arquivo CSV
        return carros;
    }
}
